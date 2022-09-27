package executor.service.flow;

import executor.model.Scenario;
import executor.service.flow.executable_flows.ProxyFlow;
import executor.service.flow.executable_flows.ScenarioFlow;
import executor.service.flow.executable_flows.WorkerFlow;
import executor.service.proxy.ProxySourcesClient;
import executor.service.scenario.ScenarioExecutor;
import executor.service.scenario.ScenarioExecutorServiceImpl;
import executor.service.scenario.ScenarioSourceListener;
import executor.service.scenario.ScenarioSourceListenerImpl;
import executor.service.factory.Factory;
import executor.service.factory.DIFactory;
import executor.model.ProxyConfigHolder;
import executor.service.proxy.ProxySourcesClientJson;
import executor.service.web_driver.ChromeWebDriverInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FlowRunnerImpl implements CommandLineRunner {
    private static final FlowRunnerImpl INSTANCE = new FlowRunnerImpl();
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowRunnerImpl.class);

    private static final ParallelFlowExecuteService FLOW_EXECUTOR;
    private static final ScenarioExecutor SCENARIO_EXECUTOR;
    private static final ProxySourcesClient PROXY_SOURCES_CLIENT;
    private static final ChromeWebDriverInitializer CHROME_WEB_DRIVER_INITIALIZER;
    private static final ScenarioSourceListener SCENARIO_SOURCE_LISTENER;

    @Autowired
    WorkerFlow workerFlow;
    @Autowired
    ScenarioFlow scenarioFlow;
    @Autowired
    ProxyFlow proxyFlow;

    public FlowRunnerImpl() {  }

    static {
        Factory factory = DIFactory.getInstance();
        FLOW_EXECUTOR = factory.getInstance(ParallelFlowExecuteServiceImpl.class);
        SCENARIO_EXECUTOR = factory.getInstance(ScenarioExecutorServiceImpl.class);
        PROXY_SOURCES_CLIENT = factory.getInstance(ProxySourcesClientJson.class);
        CHROME_WEB_DRIVER_INITIALIZER = factory.getInstance(ChromeWebDriverInitializer.class);
        SCENARIO_SOURCE_LISTENER = factory.getInstance(ScenarioSourceListenerImpl.class);
    }

    public static FlowRunnerImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void run(String... args) {
        Queue<Scenario> scenarioQueue = new ConcurrentLinkedQueue<>();
        Queue<ProxyConfigHolder> proxyQueue = new ConcurrentLinkedQueue<>();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                CompletableFuture<Scenario> futureScenario = scenarioFlow.getScenario();
                CompletableFuture<ProxyConfigHolder> futureProxyConfig = proxyFlow.getProxy();

                if (futureProxyConfig.isDone() && futureScenario.isDone()) {
                    new WorkerFlow(CHROME_WEB_DRIVER_INITIALIZER, SCENARIO_EXECUTOR).work(
                            futureScenario.get(), futureProxyConfig.get()
                    );
                } else {
                    if (!scenarioQueue.isEmpty() && !proxyQueue.isEmpty()) {
                        new WorkerFlow(CHROME_WEB_DRIVER_INITIALIZER, SCENARIO_EXECUTOR).work(
                                scenarioQueue.poll(), proxyQueue.poll()
                        );
                    }
                    scenarioQueue.add(futureScenario.get());
                    proxyQueue.add(futureProxyConfig.get());
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }
}

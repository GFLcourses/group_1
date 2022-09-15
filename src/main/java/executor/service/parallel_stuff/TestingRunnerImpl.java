package executor.service.parallel_stuff;

import executor.service.proxy.ProxySourcesClient;
import executor.service.scenario.ScenarioExecutor;
import executor.service.scenario.ScenarioExecutorServiceImpl;
import executor.service.scenario.ScenarioSourceListener;
import executor.service.scenario.ScenarioSourceListenerImpl;
import executor.service.factory.Factory;
import executor.service.factory.DIFactory;
import executor.model.ProxyConfigHolderDto;
import executor.service.proxy.ProxySourcesClientJson;
import executor.service.web_driver.ChromeWebDriverInitializer;
import org.openqa.selenium.WebDriver;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TestingRunnerImpl implements TestingRunner {
    private static final TestingRunnerImpl INSTANCE = new TestingRunnerImpl();

    private static final ParallelFlowExecuteService FLOW_EXECUTOR;
    private static final ExecutionService EXECUTION_SERVICE;
    private static final ScenarioExecutor SCENARIO_EXECUTOR;
    private static final ProxySourcesClient PROXY_SOURCES_CLIENT;
    private static final ChromeWebDriverInitializer CHROME_WEB_DRIVER_INITIALIZER;
    private static final ScenarioSourceListener SCENARIO_SOURCE_LISTENER;

    protected TestingRunnerImpl() {  }

    static {
        Factory factory = DIFactory.getInstance();
        FLOW_EXECUTOR = factory.getInstance(ParallelFlowExecuteServiceImpl.class);
        EXECUTION_SERVICE = factory.getInstance(ExecutionServiceImpl.class);
        SCENARIO_EXECUTOR = factory.getInstance(ScenarioExecutorServiceImpl.class);
        PROXY_SOURCES_CLIENT = factory.getInstance(ProxySourcesClientJson.class);
        CHROME_WEB_DRIVER_INITIALIZER = factory.getInstance(ChromeWebDriverInitializer.class);
        SCENARIO_SOURCE_LISTENER = factory.getInstance(ScenarioSourceListenerImpl.class);
    }

    public static TestingRunnerImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void run() {
        Queue<ProxyConfigHolderDto> proxyQueue = new ConcurrentLinkedQueue<>();
        Queue<WebDriver> webDriverQueue = new ConcurrentLinkedQueue<>();
        while (!Thread.currentThread().isInterrupted()) {
            Runnable getProxies = () -> {
                try {
                    proxyQueue.add(PROXY_SOURCES_CLIENT.getProxy());
                    System.out.println(proxyQueue.peek());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            FLOW_EXECUTOR.parallelExecute(getProxies);

            Runnable getChromeDrivers = () -> {
                webDriverQueue.add(CHROME_WEB_DRIVER_INITIALIZER.initialize(proxyQueue.poll()));
            };
            FLOW_EXECUTOR.parallelExecute(getChromeDrivers);

            Runnable worker = () -> EXECUTION_SERVICE.execute(
                    webDriverQueue.poll(),
                    SCENARIO_SOURCE_LISTENER,
                    SCENARIO_EXECUTOR
            );
            FLOW_EXECUTOR.parallelExecute(worker);
        }
    }
}

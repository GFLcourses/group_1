package executor.service.parallel_stuff;

import executor.exception.NoProxyFoundException;
import executor.model.Scenario;
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
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

public class FlowRunnerImpl implements FlowRunner {
    private static final FlowRunnerImpl INSTANCE = new FlowRunnerImpl();
    private static final Logger LOGGER = LogManager.getLogger(FlowRunnerImpl.class);

    private static final ParallelFlowExecuteService FLOW_EXECUTOR;
    private static final ScenarioExecutor SCENARIO_EXECUTOR;
    private static final ProxySourcesClient PROXY_SOURCES_CLIENT;
    private static final ChromeWebDriverInitializer CHROME_WEB_DRIVER_INITIALIZER;
    private static final ScenarioSourceListener SCENARIO_SOURCE_LISTENER;

    protected FlowRunnerImpl() {  }

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

    public void run() {
        Queue<ProxyConfigHolder> proxyQueue = new ConcurrentLinkedQueue<>();
        Queue<Scenario> scenarioQueue = new ConcurrentLinkedQueue<>();

        while (!Thread.currentThread().isInterrupted()) {
            CountDownLatch scenarioCounter = new CountDownLatch(1);
            CountDownLatch proxyCounter = new CountDownLatch(1);
            CountDownLatch commonCounter = new CountDownLatch(3);

            Runnable getScenarios = () -> {
                try {
                    scenarioQueue.add(SCENARIO_SOURCE_LISTENER.getScenario());
                    scenarioCounter.countDown();
                    commonCounter.countDown();
                } catch (Exception e) {
                    System.out.println("-scenario");
                }
            };
            FLOW_EXECUTOR.parallelExecute(getScenarios);

            Runnable getProxies = () -> {
                try {
                    proxyQueue.add(PROXY_SOURCES_CLIENT.getProxy().orElseThrow(
                            ()-> new NoProxyFoundException("Proxy is null")));
                    proxyCounter.countDown();
                    commonCounter.countDown();
                } catch (Exception e) {
                    System.out.println("-proxy");
                }
            };
            FLOW_EXECUTOR.parallelExecute(getProxies);

            Runnable worker = () -> {
                WebDriver driver = null;
                try {
                    scenarioCounter.await();
                    proxyCounter.await();
                    driver = CHROME_WEB_DRIVER_INITIALIZER.initialize();
                    SCENARIO_EXECUTOR.execute(SCENARIO_SOURCE_LISTENER.getScenario(), driver);
                    commonCounter.countDown();
                    System.out.println("success'");
                } catch (Exception e) {
                    LOGGER.error("{}, {}! An exception occurred!",
                            this.getClass().getPackageName(),
                            this.getClass().getSimpleName(),
                            e);
                    assert driver != null;
                    driver.quit();
                }
            };
            FLOW_EXECUTOR.parallelExecute(worker);

            try {
                commonCounter.await();
            } catch (Exception e) {

            }
        }
    }
}

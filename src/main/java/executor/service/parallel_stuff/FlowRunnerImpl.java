package executor.service.parallel_stuff;

import executor.exception.NoProxyFoundException;
import executor.exception.NoScenarioFoundException;
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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

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

    protected FlowRunnerImpl() {
    }

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

    public void run() throws InterruptedException {
        Queue<Scenario> scenarioQueue = new ConcurrentLinkedQueue<>();
        Queue<ProxyConfigHolder> proxyQueue = new ConcurrentLinkedQueue<>();

        while (!Thread.currentThread().isInterrupted()) {
            CountDownLatch counter = new CountDownLatch(3);

            Runnable getScenario = () -> {
                counter.countDown();
                try {
                    Scenario scenario = SCENARIO_SOURCE_LISTENER.getScenario().get();
                    scenarioQueue.add(scenario);
                    LOGGER.log(Level.DEBUG, scenario.toString());
                } catch (Exception e) {
//                    LOGGER.log(Level.ERROR, e.getMessage());
                }
            };
            FLOW_EXECUTOR.parallelExecute(getScenario);

            Runnable getProxy = () -> {
                counter.countDown();
                try {
                    ProxyConfigHolder proxyConfigHolder = PROXY_SOURCES_CLIENT.getProxy().get();
                    proxyQueue.add(proxyConfigHolder);
                    LOGGER.log(Level.DEBUG, proxyConfigHolder.toString());
                } catch (Exception e) {
//                    LOGGER.log(Level.ERROR, e.getMessage());
                }
            };
            FLOW_EXECUTOR.parallelExecute(getProxy);

            if (!scenarioQueue.isEmpty() && !proxyQueue.isEmpty()) {
                counter.countDown();
                Runnable worker = () -> {
                    LOGGER.log(Level.DEBUG, "execute scenario in worker");
                    Scenario scenario = scenarioQueue.poll();
                    ProxyConfigHolder proxyConfigHolder = proxyQueue.poll();
                    LOGGER.log(Level.DEBUG, "start webDriver init");
                    System.out.println("webdriver start");
                    WebDriver webDriver = CHROME_WEB_DRIVER_INITIALIZER.initialize();
                    System.out.println("webdriver end");
                    LOGGER.log(Level.DEBUG, "end webDriver init");
                    LOGGER.log(Level.DEBUG, "execute scenario in worker: " + scenario.toString());
                    SCENARIO_EXECUTOR.execute(scenario, webDriver);
                    webDriver.quit();
                };
                FLOW_EXECUTOR.parallelExecute(worker);
            } else {
                counter.countDown();
            }
            counter.await();
        }
    }
}

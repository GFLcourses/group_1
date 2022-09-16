package executor.service.parallel_stuff;

import executor.exception.NoProxyFoundException;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

public class TestingRunnerImpl implements TestingRunner {
    private static final TestingRunnerImpl INSTANCE = new TestingRunnerImpl();
    private static final Logger LOGGER = LogManager.getLogger(TestingRunnerImpl.class);

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
            CountDownLatch counter = new CountDownLatch(2);
            CountDownLatch proxyWaiter = new CountDownLatch(1);

            Runnable getProxies = () -> {
                try {
                    proxyQueue.add(PROXY_SOURCES_CLIENT.getProxy().orElseThrow(
                            ()-> new NoProxyFoundException("Proxy is null")));
                    proxyWaiter.countDown();
                    counter.countDown();
                } catch (Exception e) {
                    LOGGER.error("{}, {}! An exception occurred!",
                            this.getClass().getPackageName(),
                            this.getClass().getSimpleName(),
                            e);
                }
            };
            FLOW_EXECUTOR.parallelExecute(getProxies);

            Runnable getChromeDrivers = () -> {
                try {
                    proxyWaiter.await();
                    System.out.println("CHROME_DRIVER_GETTING_START");
                    webDriverQueue.add(CHROME_WEB_DRIVER_INITIALIZER.initialize(proxyQueue.poll()));
                    System.out.println("CHROME_DRIVER_WAS_GOT");
                    counter.countDown();
                } catch (Exception e) {
                    LOGGER.error("{}, {}! An exception occurred!",
                            this.getClass().getPackageName(),
                            this.getClass().getSimpleName(),
                            e);
                }
            };
            FLOW_EXECUTOR.parallelExecute(getChromeDrivers);

            Runnable worker = () -> {
                try {
                    counter.await();
                    EXECUTION_SERVICE.execute(
                            webDriverQueue.poll(),
                            SCENARIO_SOURCE_LISTENER,
                            SCENARIO_EXECUTOR);
                    System.out.println("success'");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            FLOW_EXECUTOR.parallelExecute(worker);
        }
    }
}

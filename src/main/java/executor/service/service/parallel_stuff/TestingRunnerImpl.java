package executor.service.service.parallel_stuff;

import executor.service.ScenarioSourceListener;
import executor.service.ScenarioSourceListenerImpl;
import executor.service.service.factory.Factory;
import executor.service.service.factory.DIFactory;
import executor.service.model.ProxyConfigHolderDto;
import executor.service.service.*;
import executor.service.service.web_driver.ChromeWebDriverInitializer;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;
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

    static public TestingRunnerImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void run() {
        Queue<ProxyConfigHolderDto> proxyQueue = new ConcurrentLinkedQueue<>();
        Queue<WebDriver> webDriverQueue = new ConcurrentLinkedQueue<>();
        while (Thread.interrupted()) {
            Runnable getProxies = () -> {
                try {
                    proxyQueue.add(PROXY_SOURCES_CLIENT.getProxy());
                } catch (URISyntaxException | IOException e) {
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

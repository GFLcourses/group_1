package executor.service.service.execution_service_sample;

import executor.service.model.ProxyConfigHolderDto;
import executor.service.model.Scenario;
import executor.service.util.PropertiesReader;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ParallelFlowExecutorServiceV2 {
    private final ProxySourceClient proxySourceClient;
    private final ScenarioSourceListener scenarioSourceListener;
    private final WebDriverInitializer webDriverInitializer;

    private final Queue<Scenario> SCENARIO_QUEUE;
    private final Queue<ProxyConfigHolderDto> PROXY_QUEUE;

    public ParallelFlowExecutorServiceV2() {
        this.proxySourceClient = new ProxySourceClient();
        this.webDriverInitializer = new WebDriverInitializer();
        this.scenarioSourceListener = new ScenarioSourceListener();
        this.SCENARIO_QUEUE = new ConcurrentLinkedQueue<>();
        this.PROXY_QUEUE = new ConcurrentLinkedQueue<>();
    }

    public void parallelExecute() {
        var poolConfig = PropertiesReader.readThreadPoolConfig();
        var executor = new ThreadPoolExecutor(
                poolConfig.getCorePoolSize(),
                poolConfig.getCorePoolSize(),
                poolConfig.getKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );

        Runnable getProxies = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    PROXY_QUEUE.add(proxySourceClient.getProxy());
                }
            }
        };
        Runnable getScenarios = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    SCENARIO_QUEUE.add(scenarioSourceListener.getScenario());
                }
            }
        };
        executor.submit(getProxies);
        executor.submit(getScenarios);

        for (int i = 0; i < 6; i++) {
            Runnable worker = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        Object webDriver = webDriverInitializer.initialize();
//                        scenarioExecutor.execute(SCENARIO_QUEUE.poll(), webDriver);
                        Object scenarioExecutor = null;
                        new ExecutionService().execute(webDriver,  SCENARIO_QUEUE.poll(), scenarioExecutor);
                    }
                }
            };
            executor.submit(worker);
        }
    }
}

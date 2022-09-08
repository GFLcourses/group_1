package executor.service.service.execution_service_sample;

import executor.service.model.Scenario;
import executor.service.util.PropertiesReader;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.*;

public class ParallelFlowExecutorService {
    private final Queue<Scenario> SCENARIO_QUEUE;
    private final Queue<String> PROXY_QUEUE;

    public ParallelFlowExecutorService() {
        this.SCENARIO_QUEUE = new ConcurrentLinkedQueue<>();
        this.PROXY_QUEUE = new ConcurrentLinkedQueue<>();
    }

    public void run() {
        CountDownLatch counter = new CountDownLatch(8);
        var poolConfig = PropertiesReader.readThreadPoolConfig();
        var executor = new ThreadPoolExecutor(
                poolConfig.getCorePoolSize(),
                poolConfig.getCorePoolSize(),
                poolConfig.getKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );

        Runnable getScenarios = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    SCENARIO_QUEUE.add(new Scenario(
                            "scenario " + i,
                            "www.mjeesh.asd",
                            new ArrayList<>()
                    ));
                }
                counter.countDown();
            }
        };
        Runnable getProxies = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    PROXY_QUEUE.add("proxy " + i);
                }
            }
        };
        executor.submit(getScenarios);
        executor.submit(getProxies);

        for (int i = 0; i < 6; i++) {
            Runnable worker = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        var proxy = PROXY_QUEUE.poll();
                        var scenario = SCENARIO_QUEUE.poll();
                        new ExecutionService().execute(null, null, scenario);
                    }
                    counter.countDown();
                }
            };
            executor.submit(worker);
        }
    }
}

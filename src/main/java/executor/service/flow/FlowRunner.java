package executor.service.flow;

import executor.model.Scenario;
import executor.service.flow.executable_flows.ProxyFlow;
import executor.service.flow.executable_flows.ScenarioFlow;
import executor.service.flow.executable_flows.WorkerFlow;
import executor.model.ProxyConfigHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@Qualifier("flowRunner")
public class FlowRunner implements CommandLineRunner {
    private static final Logger LOGGER = LogManager.getLogger(FlowRunner.class);
    private final ScenarioFlow scenarioFlow;
    private final ProxyFlow proxyFlow;
    private final WorkerFlow workerFlow;

    @Autowired
    public FlowRunner(WorkerFlow workerFlow, ScenarioFlow scenarioFlow, ProxyFlow proxyFlow) {
        this.workerFlow = workerFlow;
        this.scenarioFlow = scenarioFlow;
        this.proxyFlow = proxyFlow;
    }

    @Override
    public void run(String... args) {
        Queue<Scenario> scenarioQueue = new ConcurrentLinkedQueue<>();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                CompletableFuture<Scenario> futureScenario = scenarioFlow.execute();
                CompletableFuture<ProxyConfigHolder> futureProxyConfig = proxyFlow.execute();

                var scenario = futureScenario.get();
                if (!scenario.isNull()) {
                    scenarioQueue.add(scenario);

                    var proxyConfig = futureProxyConfig.get();
                    if (!scenarioQueue.isEmpty()) {
                        workerFlow.work(scenarioQueue.poll(), proxyConfig);
                    } else {
                        workerFlow.work(scenarioQueue.poll(), null);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("test")) {
                    return;
                }
            }
        }
    }
}

package executor.service.flow.executable_flows;

import executor.model.Scenario;
import executor.service.scenario.ScenarioSourceListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ScenarioFlow implements ExecutableFlow<Scenario> {
    private final ScenarioSourceListener scenarioSourceListener;

    @Autowired
    public ScenarioFlow(ScenarioSourceListener scenarioSourceListener) {
        this.scenarioSourceListener = scenarioSourceListener;
    }

    @Async
    @Override
    public CompletableFuture<Scenario> execute() {
        Scenario scenario = this.scenarioSourceListener.getScenario();
        return CompletableFuture.completedFuture(scenario);
    }
}

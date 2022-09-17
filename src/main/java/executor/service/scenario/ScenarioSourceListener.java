package executor.service.scenario;

import executor.model.Scenario;

import java.util.Optional;

public interface ScenarioSourceListener {
    Optional<Scenario> getScenario();
}

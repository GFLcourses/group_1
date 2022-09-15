package executor.service.service.scenario;

import executor.service.model.Scenario;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ScenarioSourceListener {
    Scenario getScenario();
}

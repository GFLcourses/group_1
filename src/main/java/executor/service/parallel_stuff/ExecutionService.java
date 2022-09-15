package executor.service.parallel_stuff;

import executor.service.scenario.ScenarioSourceListener;
import executor.service.scenario.ScenarioExecutor;
import org.openqa.selenium.WebDriver;

public interface ExecutionService {

    void execute(WebDriver webDriver, ScenarioSourceListener scenarioSourceListener, ScenarioExecutor scenarioExecutor);
}

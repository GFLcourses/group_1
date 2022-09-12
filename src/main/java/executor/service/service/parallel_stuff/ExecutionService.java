package executor.service.service.parallel_stuff;

import executor.service.ScenarioSourceListener;
import executor.service.service.ScenarioExecutor;
import org.openqa.selenium.WebDriver;

public interface ExecutionService {

    void execute(WebDriver webDriver, ScenarioSourceListener scenarioSourceListener, ScenarioExecutor scenarioExecutor);
}

package executor.service.service.parallel_stuff;

import executor.service.service.scenario.ScenarioSourceListener;
import executor.service.model.Scenario;
import executor.service.service.scenario.ScenarioExecutor;
import org.openqa.selenium.WebDriver;

public class ExecutionServiceImpl implements ExecutionService {
    private static final ExecutionServiceImpl INSTANCE = new ExecutionServiceImpl();

    protected ExecutionServiceImpl() {  }

    public static ExecutionServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(WebDriver webDriver, ScenarioSourceListener scenarioSourceListener, ScenarioExecutor scenarioExecutor) {
        Scenario scenario = scenarioSourceListener.getScenario();
        scenarioExecutor.execute(scenario, webDriver);
    }
}

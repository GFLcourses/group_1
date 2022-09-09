package executor.service.service.parallel_stuff;

import executor.service.ScenarioSourceListener;
import executor.service.service.ScenarioExecutor;
import org.openqa.selenium.WebDriver;

public class ExecutionServiceImpl implements ExecutionService {
    private static final ExecutionServiceImpl INSTANCE = new ExecutionServiceImpl();

    protected ExecutionServiceImpl() {  }

    public ExecutionServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(WebDriver webDriver, ScenarioSourceListener scenarioListener, ScenarioExecutor scenarioExecutor) {
        try {
            scenarioListener.execute(); // TODO: I must receive ScenarioDto right here, but this method return void
        } catch (Exception e) {
            // ...
        }
        scenarioExecutor.execute(null, webDriver);
    }
}

package executor.service.scenario;

import executor.model.Scenario;
import executor.model.Step;
import executor.service.step_execution.StepExecutable;
import executor.service.step_execution.StepExecutionClickCSS;
import executor.service.step_execution.StepExecutionClickXpath;
import executor.service.step_execution.StepExecutionServiceSleep;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ScenarioExecutorServiceImpl implements ScenarioExecutor {
    private static final ScenarioExecutorServiceImpl INSTANCE = new ScenarioExecutorServiceImpl();

    protected ScenarioExecutorServiceImpl() {  }

    public static ScenarioExecutorServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(Scenario scenario, WebDriver webDriver) {
        List<Step> steps = scenario.getSteps();
        for (Step step : steps) {
            String action = step.getAction();
            StepExecutable stepExecutable = null;
            switch (action){
                case "clickCss":
                    stepExecutable = new StepExecutionClickCSS();
                    stepExecutable.step(webDriver, step);
                    break;
                case "clickXpath":
                    stepExecutable = new StepExecutionClickXpath();
                    stepExecutable.step(webDriver, step);
                    break;
                case "sleep":
                    stepExecutable = new StepExecutionServiceSleep();
                    stepExecutable.step(webDriver, step);
                    break;
                default:
                    stepExecutable.step(webDriver, step);
            }
        }
    }
}

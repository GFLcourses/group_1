package executor.service.service;

import executor.service.model.Scenario;
import executor.service.model.Step;
import executor.service.service.step_execution.StepExecutable;
import executor.service.service.step_execution.StepExecutionClickCSS;
import executor.service.service.step_execution.StepExecutionClickXpath;
import executor.service.service.step_execution.StepExecutionServiceSleep;
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
                case "clickCSS":
                    stepExecutable = new StepExecutionClickCSS();
                    break;
                case "clickXpath":
                    stepExecutable = new StepExecutionClickXpath();
                    break;
                case "sleep":
                    stepExecutable = new StepExecutionServiceSleep();
                    break;
                default:
                    stepExecutable.step(webDriver, step);
            }
        }
    }
}

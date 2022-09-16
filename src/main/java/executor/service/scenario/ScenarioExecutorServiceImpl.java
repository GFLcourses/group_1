package executor.service.scenario;

import executor.exception.UnknownActionException;
import executor.model.Scenario;
import executor.model.Step;
import executor.service.factory.DIFactory;
import executor.service.factory.Factory;
import executor.service.step_execution.StepExecutionClickCSS;
import executor.service.step_execution.StepExecutionClickXpath;
import executor.service.step_execution.StepExecutionServiceSleep;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ScenarioExecutorServiceImpl implements ScenarioExecutor {
    private static final ScenarioExecutorServiceImpl INSTANCE = new ScenarioExecutorServiceImpl();
    private static final StepExecutionClickCSS STEP_EXECUTION_CLICK_CSS;
    private static final StepExecutionClickXpath STEP_EXECUTION_CLICK_XPATH;
    private static final StepExecutionServiceSleep STEP_EXECUTION_SERVICE_SLEEP;

    protected ScenarioExecutorServiceImpl() {  }

    static {
        Factory factory = DIFactory.getInstance();
        STEP_EXECUTION_CLICK_CSS = factory.getInstance(StepExecutionClickCSS.class);
        STEP_EXECUTION_CLICK_XPATH = factory.getInstance(StepExecutionClickXpath.class);
        STEP_EXECUTION_SERVICE_SLEEP = factory.getInstance(StepExecutionServiceSleep.class);
    }

    public static ScenarioExecutorServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(Scenario scenario, WebDriver webDriver) {
        webDriver.get(scenario.getSite());
        List<Step> steps = scenario.getSteps();
        for (Step step : steps) {
            String action = step.getAction();
            switch (action) {
                case "clickCss":
                    STEP_EXECUTION_CLICK_CSS.step(webDriver, step);
                    break;
                case "clickXpath":
                    STEP_EXECUTION_CLICK_XPATH.step(webDriver, step);
                    break;
                case "sleep":
                    STEP_EXECUTION_SERVICE_SLEEP.step(webDriver, step);
                    break;
                default:
                    throw new UnknownActionException(String.format("Action with name=%s is unfamiliar", action));
            }
        }
    }
}

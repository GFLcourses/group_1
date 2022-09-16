package executor.service.step_execution;

import executor.model.Step;
import org.openqa.selenium.WebDriver;

public class StepExecutionClickXpath implements StepExecutable {
    private static final StepExecutionClickXpath INSTANCE = new StepExecutionClickXpath();

    protected StepExecutionClickXpath() {  }

    public static StepExecutionClickXpath getInstance() {
        return INSTANCE;
    }

    @Override
    public String getStepAction() {
        return "clickXpath";
    }

    @Override
    public void step(WebDriver webDriver, Step step) {
        webDriver.navigate().to(step.getValue());
    }
}

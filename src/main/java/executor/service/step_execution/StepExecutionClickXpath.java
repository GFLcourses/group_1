package executor.service.step_execution;

import executor.model.Step;
import org.openqa.selenium.WebDriver;

public class StepExecutionClickXpath implements StepExecutable {
    @Override
    public String getStepAction() {
        return "clickXpath";
    }

    @Override
    public void step(WebDriver webDriver, Step step) {
        webDriver.navigate().to(step.getValue());
    }
}

package executor.service.service.step_execution;

import executor.service.model.Step;
import org.openqa.selenium.WebDriver;

public class StepExecutionClickXpath implements StepExecutable {
    @Override
    public String getStepAction() {
        return "click";
    }

    @Override
    public void step(WebDriver webDriver, Step step) {
        webDriver.navigate().to(step.getValue());
    }
}

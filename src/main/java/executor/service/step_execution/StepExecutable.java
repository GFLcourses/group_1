package executor.service.step_execution;

import executor.model.Step;
import org.openqa.selenium.WebDriver;

public interface StepExecutable {

    String getStepAction();

    void step(WebDriver webDriver, Step step);
}

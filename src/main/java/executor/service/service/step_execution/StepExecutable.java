package executor.service.service.step_execution;

import executor.service.model.Step;
import org.openqa.selenium.WebDriver;

public interface StepExecutable {

    String getStepAction();

    void step(WebDriver webDriver, Step step);
}

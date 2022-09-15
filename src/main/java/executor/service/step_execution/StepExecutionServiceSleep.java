package executor.service.step_execution;

import executor.model.Step;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class StepExecutionServiceSleep implements StepExecutable{

    @Override
    public String getStepAction() {
        return "sleep";
    }

    @Override
    public void step(WebDriver webDriver, Step step) {
        try {
            TimeUnit.SECONDS.sleep(Long.parseLong(step.getValue()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

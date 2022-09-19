package executor.service.step_execution;

import executor.model.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        if (webDriver == null || step == null) {
            return;
        }
        try {
            WebElement webElement = webDriver.findElement(By.xpath(step.getValue()));
            if (webElement.isEnabled()) {
                webElement.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

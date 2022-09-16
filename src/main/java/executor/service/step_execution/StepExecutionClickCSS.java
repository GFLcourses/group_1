package executor.service.step_execution;

import executor.model.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StepExecutionClickCSS implements StepExecutable{
    private static final StepExecutionClickCSS INSTANCE = new StepExecutionClickCSS();

    protected StepExecutionClickCSS() {  }

    public static StepExecutionClickCSS getInstance() {
        return INSTANCE;
    }

    @Override
    public String getStepAction() {
        return "clickCss";
    }

    @Override
    public void step(WebDriver webDriver, Step step) {
        if (webDriver == null || step == null) {
            return;
        }
        try {
            WebElement webElement = webDriver.findElement(By.cssSelector(step.getValue()));
            if (webElement.isEnabled()) {
                webElement.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

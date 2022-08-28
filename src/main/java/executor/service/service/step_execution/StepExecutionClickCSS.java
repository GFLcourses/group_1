package executor.service.service.step_execution;

import executor.service.model.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StepExecutionClickCSS implements StepExecutable{
    @Override
    public String getStepAction() {
        return "clickCSS";
    }

    @Override
    public void step(WebDriver webDriver, Step step) {
        WebElement webElement = webDriver.findElement(By.cssSelector(step.getValue()));
        if (webElement.isEnabled()) {
            webElement.click();
        }
    }
}
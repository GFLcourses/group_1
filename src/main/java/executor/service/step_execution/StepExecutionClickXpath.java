package executor.service.step_execution;

import executor.model.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StepExecutionClickXpath implements StepExecutable {

    @Autowired
    public StepExecutionClickXpath() {  }

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

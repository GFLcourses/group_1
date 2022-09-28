package executor.service.scenario;

import executor.model.Step;
import executor.service.step_execution.StepExecutionClickCSS;
import executor.service.step_execution.StepExecutionClickXpath;
import executor.service.step_execution.StepExecutionServiceSleep;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@Ignore
public class ScenarioExecutorServiceImplTest {
    private final StepExecutionClickCSS STEP_EXECUTION_CLICK_CSS;
    private final StepExecutionClickXpath STEP_EXECUTION_CLICK_XPATH;
    private final StepExecutionServiceSleep STEP_EXECUTION_SERVICE_SLEEP;
    private final Step stepCss;
    private final Step stepXpath;
    private final Step sleep;

    @Autowired
    public ScenarioExecutorServiceImplTest(StepExecutionClickCSS STEP_EXECUTION_CLICK_CSS, StepExecutionClickXpath STEP_EXECUTION_CLICK_XPATH, StepExecutionServiceSleep STEP_EXECUTION_SERVICE_SLEEP) {
        this.STEP_EXECUTION_CLICK_CSS = STEP_EXECUTION_CLICK_CSS;
        this.STEP_EXECUTION_CLICK_XPATH = STEP_EXECUTION_CLICK_XPATH;
        this.STEP_EXECUTION_SERVICE_SLEEP = STEP_EXECUTION_SERVICE_SLEEP;
        stepCss = new Step("clickCss", "body > ul > li:nth-child(1) > a");
        stepXpath = new Step("clickXpath", "/html/body/p");
        sleep = new Step("sleep", "2");
    }

    @Test
    public void execute() {
        WebDriver webDriver = mock(WebDriver.class);
        STEP_EXECUTION_CLICK_CSS.step(webDriver, stepCss);
        verify(webDriver, times(1)).findElement(By.cssSelector("body > ul > li:nth-child(1) > a"));
        STEP_EXECUTION_CLICK_XPATH.step(webDriver, stepXpath);
        verify(webDriver, times(1)).findElement(By.xpath("/html/body/p"));

        int expected = Integer.parseInt(sleep.getValue());
        long startTime = System.currentTimeMillis();
        STEP_EXECUTION_SERVICE_SLEEP.step(webDriver, sleep);
        long finishTime = System.currentTimeMillis();
        int actual = (int) (finishTime - startTime) / 1000;
        assertEquals(expected, actual);
    }
}
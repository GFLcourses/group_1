package executor.service.scenario;

import executor.model.Scenario;
import executor.model.Step;
import executor.service.factory.DIFactory;
import executor.service.factory.Factory;
import executor.service.step_execution.StepExecutionClickCSS;
import executor.service.step_execution.StepExecutionClickXpath;
import executor.service.step_execution.StepExecutionServiceSleep;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ScenarioExecutorServiceImplTest {
    private static final ScenarioExecutorServiceImpl INSTANCE = new ScenarioExecutorServiceImpl();
    private static StepExecutionClickCSS STEP_EXECUTION_CLICK_CSS;
    private static StepExecutionClickXpath STEP_EXECUTION_CLICK_XPATH;
    private static StepExecutionServiceSleep STEP_EXECUTION_SERVICE_SLEEP;
    private static Step stepCss;
    private static Step stepXpath;
    private static Step sleep;

    @Before
    public void setUp() throws Exception {
        Factory factory = DIFactory.getInstance();
        STEP_EXECUTION_CLICK_CSS = factory.getInstance(StepExecutionClickCSS.class);
        STEP_EXECUTION_CLICK_XPATH = factory.getInstance(StepExecutionClickXpath.class);
        STEP_EXECUTION_SERVICE_SLEEP = factory.getInstance(StepExecutionServiceSleep.class);
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
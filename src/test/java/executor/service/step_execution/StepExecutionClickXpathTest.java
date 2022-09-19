package executor.service.step_execution;

import executor.model.Step;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StepExecutionClickXpathTest {

    private StepExecutionClickXpath stepExecutionClickXpath;
    private static final String ACTION = "clickXpath";
    private static final String VALUE = "/html/body/p";

    @Before
    public void setup() {
        stepExecutionClickXpath = new StepExecutionClickXpath();
    }

    @Test
    public void getActionTest() {
        assertEquals(ACTION, stepExecutionClickXpath.getStepAction());
    }

    @Test
    public void stepTest() {
        WebDriver webDriver = mock(WebDriver.class);
        Step step = new Step(ACTION, VALUE);
        stepExecutionClickXpath.step(webDriver, step);
        verify(webDriver, times(1)).
                findElement(By.xpath(step.getValue()));
    }
}

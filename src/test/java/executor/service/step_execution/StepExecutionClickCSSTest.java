package executor.service.step_execution;

import executor.model.Step;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.mockito.Mockito.*;

@Ignore
public class StepExecutionClickCSSTest {
    private final StepExecutionClickCSS stepExecutionClickCSS;
    private static Step step;

    public StepExecutionClickCSSTest(StepExecutionClickCSS stepExecutionClickCSS) {
        this.stepExecutionClickCSS = stepExecutionClickCSS;
    }

    @Before
    public void setUp() throws Exception {
        step = new Step("clickCss", "body > ul > li:nth-child(1) > a");
    }

    @Test
    public void step() {
        WebDriver webDriver = mock(WebDriver.class);
        stepExecutionClickCSS.step(webDriver, step);
        verify(webDriver, times(1)).findElement(By.cssSelector("body > ul > li:nth-child(1) > a"));
    }
}
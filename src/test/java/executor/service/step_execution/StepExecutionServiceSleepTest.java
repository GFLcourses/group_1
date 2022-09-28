package executor.service.step_execution;

import executor.model.Step;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Ignore
public class StepExecutionServiceSleepTest {
    private static StepExecutionServiceSleep stepExecutionServiceSleep;
    private static final String ACTION = "sleep";
    private static final String VALUE = "2";

    @Before
    public void setup() {
        stepExecutionServiceSleep = new StepExecutionServiceSleep();
    }

    @Test
    public void getActionTest() {
        assertEquals(ACTION, stepExecutionServiceSleep.getStepAction());
    }

    @Test
    public void stepTest() {
        Step step = new Step(ACTION, VALUE);
        int expected = Integer.parseInt(VALUE);

        long startTime = System.currentTimeMillis();
        stepExecutionServiceSleep.step(null, step);
        long finishTime = System.currentTimeMillis();

        int actual = (int) (finishTime - startTime) / 1000;
        assertEquals(expected, actual);
    }
}

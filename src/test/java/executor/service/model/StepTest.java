package executor.service.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StepTest {
    private static Step step;
    private static Step newStep;
    private static final String ACTION = "sleep";
    private static final String VALUE = "4000";

    @Before
    public void setUp() throws Exception {
        step = new Step(ACTION, VALUE);
        newStep = new Step(ACTION, VALUE);
    }

    @Test
    public void getActionTest() {
        assertEquals(ACTION, step.getAction());
    }

    @Test
    public void getValueTest() {
        assertEquals(VALUE, step.getValue());
    }

    @Test
    public void setActionTest() {
        newStep.setAction("join");
        assertNotEquals(step.getAction(), newStep.getAction());
    }

    @Test
    public void setValueTest() {
        newStep.setValue("2000");
        assertNotEquals(step.getValue(), newStep.getValue());
    }

    @Test
    public void equalsTest() {
        assertEquals(step, newStep);
        newStep.setValue("3000");
        assertNotEquals(step, newStep);
    }

    @Test
    public void hashCodeTest() {
        assertEquals(step.hashCode(), newStep.hashCode());
        newStep.setValue("3000");
        assertNotEquals(step.hashCode(), newStep.hashCode());
    }
}
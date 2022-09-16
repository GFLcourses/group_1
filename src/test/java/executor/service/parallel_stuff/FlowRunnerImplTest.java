package executor.service.parallel_stuff;

import junit.framework.TestCase;

import static org.mockito.Mockito.*;

public class FlowRunnerImplTest extends TestCase {

    public void testTestRun() {
        FlowRunner flowRunner = mock(FlowRunnerImpl.class);
        flowRunner.run();
        verify(flowRunner, times(1)).run();
    }
}
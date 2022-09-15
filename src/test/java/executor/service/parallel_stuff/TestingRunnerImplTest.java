package executor.service.parallel_stuff;

import junit.framework.TestCase;

import static org.mockito.Mockito.*;

public class TestingRunnerImplTest extends TestCase {

    public void testTestRun() {
        TestingRunner testingRunner = mock(TestingRunnerImpl.class);
        testingRunner.run();
        verify(testingRunner, times(1)).run();
    }
}
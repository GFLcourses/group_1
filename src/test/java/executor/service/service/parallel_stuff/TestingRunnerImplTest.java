package executor.service.service.parallel_stuff;

import executor.service.service.parallel_stuff.TestingRunner;
import executor.service.service.parallel_stuff.TestingRunnerImpl;
import junit.framework.TestCase;

import static org.mockito.Mockito.*;

public class TestingRunnerImplTest extends TestCase {

    public void testTestRun() {
        TestingRunner testingRunner = mock(TestingRunnerImpl.class);
        testingRunner.run();
        verify(testingRunner, times(1)).run();
    }
}
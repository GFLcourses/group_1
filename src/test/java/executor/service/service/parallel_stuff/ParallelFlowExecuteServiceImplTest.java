package executor.service.service.parallel_stuff;

import executor.service.service.parallel_stuff.ParallelFlowExecuteServiceImpl;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ParallelFlowExecuteServiceImplTest {
    private final ParallelFlowExecuteServiceImpl parallelFlowExecuteService =
            ParallelFlowExecuteServiceImpl.getInstance();

    @Test
    public void parallelExecuteTest() {
        Runnable runnableMock = mock(Runnable.class);
        parallelFlowExecuteService.parallelExecute(runnableMock, runnableMock);
        verify(runnableMock, times(1)).run();
    }
}

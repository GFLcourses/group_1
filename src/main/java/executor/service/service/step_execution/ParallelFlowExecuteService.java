package executor.service.service.step_execution;

public interface ParallelFlowExecuteService {

    void parallelExecute(Runnable runnable);
}

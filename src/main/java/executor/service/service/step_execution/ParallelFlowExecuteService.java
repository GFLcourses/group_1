package executor.service.service.step_execution;

public interface ParallelFlowExecuteService {

    void parallelExecute(Runnable task);

    void parallelExecute(Runnable task, Runnable testCallBack);
}

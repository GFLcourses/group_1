package executor.service.flow;

public interface ParallelFlowExecuteService {

    void parallelExecute(Runnable task);
}

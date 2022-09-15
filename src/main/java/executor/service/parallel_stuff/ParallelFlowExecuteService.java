package executor.service.parallel_stuff;

public interface ParallelFlowExecuteService {

    void parallelExecute(Runnable task);
}

package executor.service.service.start_process_sample;

import executor.service.service.parallel_stuff.ParallelFlowExecuteService;
import executor.service.service.parallel_stuff.ParallelFlowExecuteServiceImpl;

public class TestRunner {
    private final ParallelFlowExecuteService parallelFlowExecuteService;

    public TestRunner() {
        this.parallelFlowExecuteService = ParallelFlowExecuteServiceImpl.getInstance();
    }

    public void run() {

    }
}

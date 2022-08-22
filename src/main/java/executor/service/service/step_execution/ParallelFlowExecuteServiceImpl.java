package executor.service.service.step_execution;

import executor.service.model.ThreadPoolConfigDto;
import executor.service.util.PropertiesReader;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ParallelFlowExecuteServiceImpl implements ParallelFlowExecuteService {
    private static final ParallelFlowExecuteServiceImpl INSTANCE;
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR;

    protected ParallelFlowExecuteServiceImpl() {  }

    static {
        ThreadPoolConfigDto poolConfig = PropertiesReader.readThreadPoolConfig();
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
                poolConfig.getCorePoolSize(),
                poolConfig.getCorePoolSize(),
                poolConfig.getKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );
        INSTANCE = new ParallelFlowExecuteServiceImpl();
    }

    public static ParallelFlowExecuteServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void parallelExecute(Runnable runnable) {
        THREAD_POOL_EXECUTOR.submit(runnable);
    }
}

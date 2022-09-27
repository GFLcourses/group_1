package executor.service.parallel_stuff;

import executor.model.ThreadPoolConfig;
import executor.util.PropertiesReader;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ParallelFlowExecuteServiceImpl implements ParallelFlowExecuteService {
    private static final ParallelFlowExecuteServiceImpl INSTANCE;
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR;

    private static Integer corePoolSize = 5;
    private static Long keepAliveTime = 6000_000L;

    protected ParallelFlowExecuteServiceImpl() {  }

    static {
//        ThreadPoolConfig poolConfig = PropertiesReader.readThreadPoolConfig();
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
                corePoolSize,
                corePoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );
        INSTANCE = new ParallelFlowExecuteServiceImpl();
    }

    public static ParallelFlowExecuteServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public synchronized void parallelExecute(Runnable task) {
        this.parallelExecute(task, null);
    }

    protected void parallelExecute(Runnable task, Runnable testCallBack) {
        if (testCallBack != null) {
            testCallBack.run();
        } else {
            THREAD_POOL_EXECUTOR.submit(task);
        }
    }
}

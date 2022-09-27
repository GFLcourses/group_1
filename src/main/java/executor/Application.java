package executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Application implements AsyncConfigurer {

    @Value("${threadpool.size}")
    private Integer threadPoolSize;
    @Value("${threadpool.aliveTime}")
    private Integer keepAliveTime;
    @Value("${threadpool.prefix}")
    private String threadPoolPrefix;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolSize);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setThreadNamePrefix(threadPoolPrefix);
        executor.initialize();
        return executor;
    }
}

package executor.config;

import executor.service.flow.FlowRunnerImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;

@Configuration
public class ApplicationConfig {

    @Bean
    CommandLineRunner getCommandLineRunner() {
        return new FlowRunnerImpl();
    }
}

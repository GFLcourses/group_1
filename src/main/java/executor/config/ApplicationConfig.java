package executor.config;

import executor.service.flow.FlowRunnerImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    CommandLineRunner getCommandLineRunner() {
        return new FlowRunnerImpl();
    }
}

package executor.config;

import executor.service.parallel_stuff.FlowRunnerImpl;
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

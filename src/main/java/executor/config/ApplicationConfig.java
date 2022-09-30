package executor.config;

import executor.service.flow.FlowRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    private final ApplicationContext applicationContext;

    @Autowired
    public ApplicationConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    CommandLineRunner getCommandLineRunner() {
        return this.applicationContext.getBean("flowRunner", FlowRunner.class);
    }
}

package executor.service.flow.executable_flows;

import executor.model.ProxyConfigHolder;
import executor.model.Scenario;
import executor.service.scenario.ScenarioExecutor;
import executor.service.web_driver.ChromeWebDriverInitializer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class WorkerFlow {
    private static final Logger LOGGER = LogManager.getLogger(WorkerFlow.class);
    private final ChromeWebDriverInitializer webDriverInitializer;
    private final ScenarioExecutor scenarioExecutor;

    @Autowired
    public WorkerFlow(ChromeWebDriverInitializer webDriverInitializer, ScenarioExecutor scenarioExecutor) {
        this.webDriverInitializer = webDriverInitializer;
        this.scenarioExecutor = scenarioExecutor;
    }

    @Async(value = "taskScheduler")
    public void work(Scenario scenario, ProxyConfigHolder proxyConfigHolder) {
        WebDriver webDriver = null;
        try {
            LOGGER.log(Level.INFO, "start webDriver init");
            System.out.println("webdriver start");
            webDriver = webDriverInitializer.initialize();
            System.out.println("webdriver end");
            LOGGER.log(Level.INFO, "end webDriver init");
            LOGGER.log(Level.INFO, "start execute scenario in worker: " + scenario.toString());
            scenarioExecutor.execute(scenario, webDriver);
            LOGGER.log(Level.INFO, "end execute scenario in worker");
            webDriver.quit();
        } catch (Exception e) {
            assert webDriver != null;
            webDriver.quit();
            throw new RuntimeException(e);
        }
    }
}

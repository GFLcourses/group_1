package executor.service.flow.executable_flows;

import executor.model.ProxyConfigHolder;
import executor.model.Scenario;
import executor.service.scenario.ScenarioExecutor;
import executor.service.web_driver.ChromeWebDriverInitializer;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class WorkerFlow {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerFlow.class);
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
            LOGGER.info("start webDriver init");
            System.out.println("webdriver start");
            webDriver = webDriverInitializer.initialize();
            System.out.println("webdriver end");
            LOGGER.info("end webDriver init");
            LOGGER.info("start execute scenario in worker: " + scenario.toString());
            scenarioExecutor.execute(scenario, webDriver);
            LOGGER.info("end execute scenario in worker");
            webDriver.quit();
        } catch (Exception e) {
            assert webDriver != null;
            webDriver.quit();
            throw new RuntimeException(e);
        }
    }
}

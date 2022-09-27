package executor.service.flow.executable_flows;

import executor.model.ProxyConfigHolder;
import executor.model.Scenario;
import executor.service.scenario.ScenarioExecutor;
import executor.service.web_driver.ChromeWebDriverInitializer;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class WorkerFlow {
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
            webDriver = webDriverInitializer.initialize();
            scenarioExecutor.execute(scenario, webDriver);
            webDriver.quit();
        } catch (Exception e) {
            assert webDriver != null;
            webDriver.quit();
            throw new RuntimeException(e);
        }
    }
}

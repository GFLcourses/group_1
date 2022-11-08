package executor.service.flow.executable_flows;

import executor.model.ProxyConfigHolder;
import executor.model.Scenario;
import executor.service.scenario.ScenarioExecutor;
import executor.service.web_driver.ChromeWebDriverInitializer;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WorkerFlow {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerFlow.class);
    private final ChromeWebDriverInitializer webDriverInitializer;
    private final ScenarioExecutor scenarioExecutor;

    @Autowired
    public WorkerFlow(ChromeWebDriverInitializer webDriverInitializer, ScenarioExecutor scenarioExecutor) {
        this.webDriverInitializer = webDriverInitializer;
        this.scenarioExecutor = scenarioExecutor;
    }

    @Async
    public void work(Scenario scenario, ProxyConfigHolder proxyConfigHolder) {
        WebDriver webDriver = null;
        try {
            LOGGER.info("start webDriver init");

            if (!proxyConfigHolder.isNull()) {
                LOGGER.info("proxy: " + proxyConfigHolder);
                System.out.println(proxyConfigHolder);
                webDriver = webDriverInitializer.initialize(proxyConfigHolder);
            } else {
                webDriver = webDriverInitializer.initialize();
            }

            LOGGER.info("end webDriver init");
            LOGGER.info("start execute scenario in worker: " + scenario);
            System.out.println("webdriver start");

            scenarioExecutor.execute(scenario, webDriver);

            System.out.println("webdriver end");
            LOGGER.info("end execute scenario in worker");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warn(e.toString());
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }
}

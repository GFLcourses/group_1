package executor.service.scenario;

import executor.exception.UnknownActionException;
import executor.model.Scenario;
import executor.model.Step;
import executor.service.step_execution.StepExecutionClickCSS;
import executor.service.step_execution.StepExecutionClickXpath;
import executor.service.step_execution.StepExecutionServiceSleep;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioExecutorServiceImpl implements ScenarioExecutor {
    private static final Logger LOGGER = LogManager.getLogger(ScenarioExecutorServiceImpl.class);
    private final StepExecutionClickCSS stepExecutionClickCSS;
    private final StepExecutionClickXpath stepExecutionClickXpath;
    private final StepExecutionServiceSleep stepExecutionServiceSleep;

    @Autowired
    public ScenarioExecutorServiceImpl(StepExecutionClickCSS stepExecutionClickCSS, StepExecutionClickXpath stepExecutionClickXpath, StepExecutionServiceSleep stepExecutionServiceSleep) {
        this.stepExecutionClickCSS = stepExecutionClickCSS;
        this.stepExecutionClickXpath = stepExecutionClickXpath;
        this.stepExecutionServiceSleep = stepExecutionServiceSleep;
    }

    @Override
    public void execute(Scenario scenario, WebDriver webDriver) {
        LOGGER.log(Level.DEBUG,"start execute scenario");
        webDriver.get(scenario.getSite());
        LOGGER.log(Level.DEBUG,"executed scenario: " + scenario.toString());
        List<Step> steps = scenario.getSteps();
        for (Step step : steps) {
            String action = step.getAction();
            switch (action) {
                case "clickCss":
                    LOGGER.log(Level.DEBUG, "start execute step CSS");
                    this.stepExecutionClickCSS.step(webDriver, step);
                    LOGGER.log(Level.DEBUG, "end execute step CSS");
                    break;
                case "clickXpath":
                    LOGGER.log(Level.DEBUG, "start execute step XPATH");
                    this.stepExecutionClickXpath.step(webDriver, step);
                    LOGGER.log(Level.DEBUG, "end execute step XPATH");
                    break;
                case "sleep":
                    LOGGER.log(Level.DEBUG, "start execute step SLEEP");
                    this.stepExecutionServiceSleep.step(webDriver, step);
                    LOGGER.log(Level.DEBUG, "end execute step SLEEP");
                    break;
                default:
                    throw new UnknownActionException(String.format("Action with name=%s is unfamiliar", action));
            }
        }
    }
}

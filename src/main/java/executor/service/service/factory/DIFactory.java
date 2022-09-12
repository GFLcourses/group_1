package executor.service.service.factory;

import executor.service.ScenarioSourceListenerImpl;
import executor.service.service.web_driver.ChromeWebDriverInitializer;
import executor.service.service.parallel_stuff.ParallelFlowExecuteServiceImpl;
import executor.service.service.step_execution.StepExecutionClickCSS;
import executor.service.service.step_execution.StepExecutionClickXpath;
import executor.service.service.step_execution.StepExecutionServiceSleep;

import java.util.HashMap;
import java.util.Map;

public class DIFactory implements Factory {
    private static final DIFactory INSTANCE = new DIFactory();
    private static final Map<Class<?>,Object> mapOfInstances = new HashMap<>();

    protected DIFactory() {  }

    public static DIFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public <T> T getInstance(Class<T> clazz) {
        if(ParallelFlowExecuteServiceImpl.class.isAssignableFrom(clazz)) {
            return (T) mapOfInstances.getOrDefault(clazz, ParallelFlowExecuteServiceImpl.getInstance());
        }
        if(StepExecutionClickCSS.class.isAssignableFrom(clazz)) {
            return (T) mapOfInstances.getOrDefault(clazz, new StepExecutionClickCSS());
        }
        if(StepExecutionClickXpath.class.isAssignableFrom(clazz)) {
            return (T) mapOfInstances.getOrDefault(clazz, new StepExecutionClickXpath());
        }
        if(StepExecutionServiceSleep.class.isAssignableFrom(clazz)) {
            return (T) mapOfInstances.getOrDefault(clazz, new StepExecutionServiceSleep());
        }
        if(ChromeWebDriverInitializer.class.isAssignableFrom(clazz)) {
            return (T) mapOfInstances.getOrDefault(clazz,new ChromeWebDriverInitializer());
        }
        if (ScenarioSourceListenerImpl.class.isAssignableFrom(clazz)) {
            return (T) mapOfInstances.getOrDefault(clazz,new ScenarioSourceListenerImpl());
        }
        throw new RuntimeException("Please, check class name before creation in factory");
    }
}
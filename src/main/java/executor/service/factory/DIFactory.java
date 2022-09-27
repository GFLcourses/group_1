package executor.service.factory;

import executor.exception.UnknownClassException;
import executor.service.flow.ParallelFlowExecuteServiceImpl;
import executor.service.flow.FlowRunnerImpl;
import executor.service.scenario.ScenarioSourceListenerImpl;
import executor.service.proxy.ProxySourcesClientJson;
import executor.service.scenario.ScenarioExecutorServiceImpl;
import executor.service.web_driver.ChromeWebDriverInitializer;
import executor.service.step_execution.StepExecutionClickCSS;
import executor.service.step_execution.StepExecutionClickXpath;
import executor.service.step_execution.StepExecutionServiceSleep;

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
            return (T) mapOfInstances.getOrDefault(clazz, StepExecutionClickCSS.getInstance());
        }
        if(StepExecutionClickXpath.class.isAssignableFrom(clazz)) {
            return (T) mapOfInstances.getOrDefault(clazz, StepExecutionClickXpath.getInstance());
        }
        if(StepExecutionServiceSleep.class.isAssignableFrom(clazz)) {
            return (T) mapOfInstances.getOrDefault(clazz, StepExecutionServiceSleep.getInstance());
        }
        if(ChromeWebDriverInitializer.class.isAssignableFrom(clazz)) {
            return (T) mapOfInstances.getOrDefault(clazz, ChromeWebDriverInitializer.getInstance());
        }
        if (ScenarioSourceListenerImpl.class.isAssignableFrom(clazz)) {
            return (T) mapOfInstances.getOrDefault(clazz, ScenarioSourceListenerImpl.getInstance());
        }
        if (ScenarioExecutorServiceImpl.class.isAssignableFrom(clazz)) {
            return (T) mapOfInstances.getOrDefault(clazz, ScenarioExecutorServiceImpl.getInstance());
        }
        if (ProxySourcesClientJson.class.isAssignableFrom(clazz)) {
            return (T) mapOfInstances.getOrDefault(clazz, ProxySourcesClientJson.getInstance());
        }
        if (FlowRunnerImpl.class.isAssignableFrom(clazz)) {
            return (T) mapOfInstances.getOrDefault(clazz, FlowRunnerImpl.getInstance());
        }
        throw new UnknownClassException("Please, check class name before creation in factory");
    }
}

package executor.service.service.parallel_stuff;

import executor.service.service.parallel_stuff.ExecutionService;
import executor.service.service.parallel_stuff.ExecutionServiceImpl;
import executor.service.service.scenario.ScenarioSourceListenerImpl;
import executor.service.service.scenario.ScenarioExecutorServiceImpl;
import executor.service.service.factory.DIFactory;
import executor.service.service.factory.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.openqa.selenium.WebDriver;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExecutionServiceImplTest {

    @Mock
    private WebDriver webDriver;
    @Mock
    private ScenarioSourceListenerImpl scenarioSourceListener;
    @Mock
    private ScenarioExecutorServiceImpl scenarioExecutor;
    @Mock
    private ExecutionServiceImpl mockExecutionService;

    @Test
    public void executeTest() {
        mockExecutionService.execute(webDriver, scenarioSourceListener, scenarioExecutor);
        verify(mockExecutionService, times(1)).execute(webDriver, scenarioSourceListener, scenarioExecutor);
    }
    
    @Test
    public void executeTestOfInsideComponents() {
        Factory factory = DIFactory.getInstance();
        ExecutionService executionService = factory.getInstance(ExecutionServiceImpl.class);
        executionService.execute(webDriver, scenarioSourceListener, scenarioExecutor);
        verify(scenarioSourceListener, times(1)).getScenario();
        verify(scenarioExecutor, times(1)).execute(scenarioSourceListener.getScenario(), webDriver);
    }
}
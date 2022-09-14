package executor.service.service.parallel_stuff;

import executor.service.ScenarioSourceListenerImpl;
import executor.service.model.Scenario;
import executor.service.service.ScenarioExecutorServiceImpl;
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
    private ExecutionServiceImpl executionService;

    @Test
    public void executeTest() {
        executionService.execute(webDriver, scenarioSourceListener, scenarioExecutor);
        verify(executionService, times(1)).execute(webDriver, scenarioSourceListener, scenarioExecutor);
    }
}
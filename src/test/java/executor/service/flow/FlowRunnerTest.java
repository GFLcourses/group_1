package executor.service.flow;

import executor.service.flow.executable_flows.ProxyFlow;
import executor.service.flow.executable_flows.ScenarioFlow;
import executor.service.flow.executable_flows.WorkerFlow;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class FlowRunnerTest {

    @Test
    void run() {
        WorkerFlow workerFlow = mock(WorkerFlow.class);
        ScenarioFlow scenarioFlow = mock(ScenarioFlow.class);
        ProxyFlow proxyFlow = mock(ProxyFlow.class);

        FlowRunner flowRunner = new FlowRunner(workerFlow, scenarioFlow, proxyFlow);
        flowRunner.run("test");


        verify(scenarioFlow, times(1)).execute();
        verify(proxyFlow, times(1)).execute();
    }
}

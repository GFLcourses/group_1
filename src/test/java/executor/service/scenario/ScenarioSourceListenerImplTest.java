package executor.service.scenario;

import executor.model.Scenario;
import org.junit.Ignore;

import static org.junit.Assert.*;

public class ScenarioSourceListenerImplTest {
    private static final ScenarioSourceListenerImpl scenarioSourceListener = new ScenarioSourceListenerImpl();

    @Ignore
    public void getScenarioTest() {
        Scenario scenario = scenarioSourceListener.getScenario();
        assertEquals(scenario.getName(),"test scenario 2");
        assertEquals(scenario.getSite(),"http://info.cern.ch");
        assertEquals(scenario.getSteps().size(),3);


    }
}
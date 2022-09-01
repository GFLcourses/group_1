package executor.service;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class ScenarioSourceListenerImplTest {
    private static final ScenarioSourceListenerImpl scenarioSourceListener = new ScenarioSourceListenerImpl();

    @Test
    public void executeAndGetTest() throws URISyntaxException, IOException {
        scenarioSourceListener.execute();
        assertEquals(scenarioSourceListener.getScenarioArray().length,2);
        assertEquals(scenarioSourceListener.getScenarioArray()[0].getName(),"test scenario 1");
        assertEquals(scenarioSourceListener.getScenarioArray()[0].getSteps().size(),3);


    }
}
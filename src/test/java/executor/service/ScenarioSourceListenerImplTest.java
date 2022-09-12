package executor.service;

import executor.service.model.Scenario;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ScenarioSourceListenerImplTest {
    private static final ScenarioSourceListenerImpl scenarioSourceListener = new ScenarioSourceListenerImpl();

    @Test
    public void getScenarioTest() throws URISyntaxException, IOException {
        Scenario scenario = scenarioSourceListener.getScenario();
        assertEquals(scenario.getName(),"test scenario 2");
        assertEquals(scenario.getSite(),"http://info.cern.ch");
        assertEquals(scenario.getSteps().size(),3);


    }
}
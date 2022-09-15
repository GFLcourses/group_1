package executor.model;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ScenarioTest {
    private static Scenario scenario;
    private static Scenario newScenario;
    private static final String NAME = "name";
    private static final String SITE = "site";
    private static final List<Step> STEP_LIST = new ArrayList<>(
            List.of(new Step("sleep", "2000"),
                    new Step("join", "4000")));

    @Before
    public void setUp() throws Exception {
        scenario = new Scenario(NAME, SITE, STEP_LIST);
        newScenario = new Scenario(NAME, SITE, STEP_LIST);
    }

    @Test
    public void getNameTest() {
        assertEquals(NAME, scenario.getName());
    }

    @Test
    public void setNameTest() {
        newScenario.setName("new_name");
        assertNotEquals(scenario.getName(), newScenario.getName());
    }

    @Test
    public void getSiteTest() {
        assertEquals(SITE, scenario.getSite());
    }

    @Test
    public void setSiteTest() {
        newScenario.setSite("new_site");
        assertNotEquals(scenario.getSite(), newScenario.getSite());
    }

    @Test
    public void getStepsTest() {
        assertEquals(STEP_LIST, scenario.getSteps());
    }

    @Test
    public void setStepsTest() {
        newScenario.setSteps(new ArrayList<>(
                List.of(new Step("sleep", "5000"),
                        new Step("join", "1000"))));
        assertNotEquals(scenario.getSteps(), newScenario.getSteps());
    }

    @Test
    public void equalsTest() {
        assertEquals(scenario, newScenario);
        newScenario.setName("new_name");
        assertNotEquals(scenario, newScenario);
    }

    @Test
    public void hashCodeTest() {
        assertEquals(scenario.hashCode(), newScenario.hashCode());
        newScenario.setSite("new_site");
        assertNotEquals(scenario.hashCode(), newScenario.hashCode());
    }
}

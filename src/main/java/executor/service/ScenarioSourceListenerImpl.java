package executor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.Scenario;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ScenarioSourceListenerImpl implements ScenarioSourceListener{
    private Scenario[] scenarioArray;

    public Scenario[] getScenarioArray() {
        return this.scenarioArray;
    }

    @Override
    public void execute() throws URISyntaxException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        URI uri = this.getClass().getClassLoader().getResource("someScenario.json").toURI();
        File file = new File(uri);
        this.scenarioArray = objectMapper.readValue(file,Scenario[].class);
        for (Scenario scenario : scenarioArray){
            System.out.println("scenario read: " + scenario.toString());
        }

    }
}

package executor.service.service.scenario;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.Scenario;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ScenarioSourceListenerImpl implements ScenarioSourceListener{
    private static final Queue<Scenario> scenarios = new PriorityQueue<>();
    
     {
        ObjectMapper objectMapper = new ObjectMapper();
         URI uri = null;
         try {
             uri = this.getClass().getClassLoader().getResource("someScenario.json").toURI();
         } catch (URISyntaxException e) {
             throw new RuntimeException(e);
         }
         File file = new File(uri);
         try {
             scenarios.addAll(objectMapper.readValue(file, new TypeReference<List<Scenario>>(){}));
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }

    @Override
    public Scenario getScenario() {
         return scenarios.poll();
    }
}

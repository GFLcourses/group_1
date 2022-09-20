package executor.service.scenario;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import executor.model.Scenario;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public class ScenarioSourceListenerImpl implements ScenarioSourceListener {
    private static final ScenarioSourceListenerImpl INSTANCE = new ScenarioSourceListenerImpl();
    private static final Queue<Scenario> scenarios = new PriorityQueue<>();

    protected ScenarioSourceListenerImpl() {  }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        URI uri = null;
        String s;
        try {
            uri = ScenarioSourceListenerImpl.class.getClassLoader().getResource("someScenario.json").toURI();
            s = ScenarioSourceListenerImpl.class.getClassLoader().getResource("someScenario.json").getFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        File file = new File(s);
        try {
            scenarios.addAll(objectMapper.readValue(file, new TypeReference<List<Scenario>>() {
            }));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ScenarioSourceListenerImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public synchronized Optional<Scenario> getScenario() {
        return Optional.ofNullable(scenarios.poll());
    }
}

package executor.service.scenario;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import executor.model.Scenario;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

@Service
public class ScenarioSourceListenerImpl implements ScenarioSourceListener {
    private static final ScenarioSourceListenerImpl INSTANCE = new ScenarioSourceListenerImpl();
    private static final Queue<Scenario> scenarios = new PriorityQueue<>();

    protected ScenarioSourceListenerImpl() {  }

    static {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            scenarios.addAll(objectMapper.readValue(new File("/home/ubuntu/staff/someScenario.json"), new TypeReference<List<Scenario>>() {
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

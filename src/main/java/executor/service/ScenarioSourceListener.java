package executor.service;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ScenarioSourceListener {
    void execute() throws URISyntaxException, IOException;
}

package executor.service.scenario;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.model.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import okhttp3.*;

@Service
public class ScenarioSourceListenerImpl implements ScenarioSourceListener {
    private static final OkHttpClient okHttpClient = new OkHttpClient();


    @Autowired
    public ScenarioSourceListenerImpl() {

    }


    @Override
    public synchronized Optional<Scenario> getScenario() {
        Request request = new Request.Builder()
                .get()
                .url("http://localhost:8080/getScenario")
                .build();
        Call call =okHttpClient.newCall(request);
        Scenario scenario;
        ObjectMapper objectMapper = new ObjectMapper();;
        try {
            Response response = call.execute();
            InputStream in =response.body().byteStream();
            scenario = objectMapper.readerFor(Scenario.class).readValue(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(scenario);
    }

}

package executor.service.scenario;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.model.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import okhttp3.*;

import java.util.concurrent.TimeUnit;

@Service
public class ScenarioSourceListenerImpl implements ScenarioSourceListener {
    @Value("${http.authorizationKey}")
    private String requestKey;
    @Value("${http.authorizationHeaderName}")
    private String requestHeader;
    @Value("${http.scenarioUrl}")
    private String requestUrl;
    @Value("${http.readTimeOut}")
    private Long connectionTimeAwait;

    @Autowired
    public ScenarioSourceListenerImpl() {  }

    @Override
    public synchronized Scenario getScenario() {
        try {
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(connectionTimeAwait, TimeUnit.SECONDS)
                    .readTimeout(connectionTimeAwait, TimeUnit.SECONDS)
                    .writeTimeout(connectionTimeAwait, TimeUnit.SECONDS)
                    .build();

            Request request = new Request.Builder()
                    .get()
                    .addHeader(requestHeader, requestKey)
                    .url(requestUrl)
                    .build();
            var call = okHttpClient.newCall(request);
            var response = call.execute();

            if (response.code() == HttpStatus.OK.value()) {
                return new ObjectMapper().readValue(response.body().string(), Scenario.class);
            }
            return new Scenario();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package executor.service.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.model.ProxyConfigHolder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProxySourcesClientJson implements ProxySourcesClient {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    @Value("${http.authorizationKey}")
    private String requestKey;
    @Value("${http.authorizationHeaderName}")
    private String requestHeader;
    @Value("${http.proxyUrl}")
    private String requestUrl;

    protected ProxySourcesClientJson() {  }

    @Override
    public synchronized Optional<ProxyConfigHolder> getProxy() {
        try {
            Request request = new Request.Builder()
                    .get()
                    .addHeader(requestHeader, requestKey)
                    .url(requestUrl)
                    .build();
            var call = okHttpClient.newCall(request);
            var responseBody = call.execute().body();
            var objectMapper = new ObjectMapper();
            var proxyConfig = objectMapper.readValue(responseBody.string(), ProxyConfigHolder.class);

            return Optional.ofNullable(proxyConfig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

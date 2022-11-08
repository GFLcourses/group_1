package executor.service.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.model.ProxyConfigHolder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ProxySourcesClientJson implements ProxySourcesClient {
    @Value("${http.authorizationKey}")
    private String requestKey;
    @Value("${http.authorizationHeaderName}")
    private String requestHeader;
    @Value("${http.proxyUrl}")
    private String requestUrl;
    @Value("${http.readTimeOut}")
    private Long connectionTimeAwait;

    protected ProxySourcesClientJson() {  }

    @Override
    public synchronized ProxyConfigHolder getProxy() {
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
                return new ObjectMapper().readValue(response.body().string(), ProxyConfigHolder.class);
            }
            return new ProxyConfigHolder();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

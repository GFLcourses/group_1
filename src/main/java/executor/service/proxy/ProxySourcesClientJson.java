package executor.service.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.model.ProxyCredentials;
import executor.model.ProxyNetworkConfig;
import executor.model.ProxyConfigHolder;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class ProxySourcesClientJson implements ProxySourcesClient {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    protected ProxySourcesClientJson() {  }

    @Override
    public synchronized Optional<ProxyConfigHolder> getProxy() {
        ProxyConfigHolder proxyConfigHolder = null;
        ProxyNetworkConfig proxyNetworkConfig;
        ProxyCredentials proxyCredentials;
        ObjectMapper objectMapper = new ObjectMapper();
        Request requestNetworkConfig = new Request.Builder()
                .get()
                .url("some url")
                .build();
        Call call = okHttpClient.newCall(requestNetworkConfig);
        try {
            Response responseNetworkConfig = call.execute();
            InputStream in =responseNetworkConfig.body().byteStream();
            proxyNetworkConfig = objectMapper.readerFor(ProxyNetworkConfig.class).readValue(in);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Request requestCredentials = new Request.Builder()
                .get()
                .url("some url")
                .build();
        Call call1 = okHttpClient.newCall(requestCredentials);
        try {
            Response responseCredentials = call1.execute();
            InputStream in =responseCredentials.body().byteStream();
            proxyCredentials = objectMapper.readerFor(ProxyCredentials.class).readValue(in);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        proxyConfigHolder.setProxyNetworkConfig(proxyNetworkConfig);
        proxyConfigHolder.setProxyCredentials(proxyCredentials);


        return Optional.ofNullable(proxyConfigHolder);

    }
}

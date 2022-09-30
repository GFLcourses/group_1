package executor.service.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.model.ProxyCredentials;
import executor.model.ProxyNetworkConfig;
import executor.model.ProxyConfigHolder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

@Service
public class ProxySourcesClientJson implements ProxySourcesClient {
    private static final Queue<ProxyConfigHolder> PROXIES_QUEUE = new PriorityQueue<>();

    protected ProxySourcesClientJson() {  }

    static {
        try {
            readProxies();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized Optional<ProxyConfigHolder> getProxy() {
        try {
            return Optional.ofNullable(PROXIES_QUEUE.poll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static void readProxies() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProxyCredentials[] proxyCredentials = objectMapper.readValue(new File("C:/ProxyCredentials.json"), ProxyCredentials[].class);
            ProxyNetworkConfig[] proxyNetworkConfigs = objectMapper.readValue(new File("C:/ProxyNetwork.json"), ProxyNetworkConfig[].class);

            for (int i = 0; i < proxyNetworkConfigs.length; i++) {
                PROXIES_QUEUE.add(new ProxyConfigHolder(proxyNetworkConfigs[i], proxyCredentials[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

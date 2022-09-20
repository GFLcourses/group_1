package executor.service.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.model.ProxyCredentials;
import executor.model.ProxyNetworkConfig;
import executor.model.ProxyConfigHolder;

import java.io.File;
import java.net.URI;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public class ProxySourcesClientJson implements ProxySourcesClient {
    private static final ProxySourcesClientJson INSTANCE = new ProxySourcesClientJson();
    private static final Queue<ProxyConfigHolder> PROXIES_QUEUE = new PriorityQueue<>();

    protected ProxySourcesClientJson() {  }

    static {
        try {
            readProxies();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ProxySourcesClientJson getInstance() {
        return INSTANCE;
    }

    @Override
    public synchronized Optional<ProxyConfigHolder> getProxy() {
        return Optional.ofNullable(PROXIES_QUEUE.poll());
    }

    protected static void readProxies() {
        try {
            URI credentialsURI = ProxySourcesClientJson.class.getClassLoader().getResource("ProxyCredentials.json").toURI();
            URI networkURI = ProxySourcesClientJson.class.getClassLoader().getResource("ProxyNetwork.json").toURI();

            String s = ProxySourcesClientJson.class.getClassLoader().getResource("ProxyCredentials.json").getPath();
            String s1 = ProxySourcesClientJson.class.getClassLoader().getResource("ProxyNetwork.json").getPath();

            File credentialsFile = new File(s);
            File networkFile = new File(s1);

            ObjectMapper objectMapper = new ObjectMapper();
            ProxyCredentials[] proxyCredentials = objectMapper.readValue(credentialsFile, ProxyCredentials[].class);
            ProxyNetworkConfig[] proxyNetworkConfigs = objectMapper.readValue(networkFile, ProxyNetworkConfig[].class);

            for (int i = 0; i < proxyNetworkConfigs.length; i++) {
                PROXIES_QUEUE.add(new ProxyConfigHolder(proxyNetworkConfigs[i], proxyCredentials[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

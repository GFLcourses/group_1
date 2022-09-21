package executor.service.proxy;

import executor.model.ProxyConfigHolder;
import executor.model.ProxyCredentials;
import executor.model.ProxyNetworkConfig;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProxySourcesClientJsonTest {
    private final ProxySourcesClientJson proxySourcesClientJson = ProxySourcesClientJson.getInstance();

    @Ignore
    public void getProxyTest() {
        List<ProxyConfigHolder> proxies = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            proxies.add(proxySourcesClientJson.getProxy().get());
        }

        List<ProxyConfigHolder> expectationProxies = List.of(
                new ProxyConfigHolder(new ProxyNetworkConfig("host3", 8089),
                        new ProxyCredentials("user3", "pass13")),
                new ProxyConfigHolder(new ProxyNetworkConfig("host2", 8088),
                        new ProxyCredentials("user2", "pass2")),
                new ProxyConfigHolder(new ProxyNetworkConfig("host1", 8080),
                        new ProxyCredentials("user11", "pass1"))
        );
        assertEquals(proxies, expectationProxies);
    }
}
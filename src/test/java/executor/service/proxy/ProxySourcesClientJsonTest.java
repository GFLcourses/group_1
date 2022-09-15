package executor.service.proxy;

import executor.model.ProxyConfigHolderDto;
import executor.model.ProxyCredentials;
import executor.model.ProxyNetworkConfig;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProxySourcesClientJsonTest {
    private final ProxySourcesClientJson proxySourcesClientJson = ProxySourcesClientJson.getInstance();

    @Test
    public void getProxyTest() {
        List<ProxyConfigHolderDto> proxies = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            proxies.add(proxySourcesClientJson.getProxy().get());
        }

        List<ProxyConfigHolderDto> expectationProxies = List.of(
                new ProxyConfigHolderDto(new ProxyNetworkConfig("host3", 8089),
                        new ProxyCredentials("user3", "pass13")),
                new ProxyConfigHolderDto(new ProxyNetworkConfig("host2", 8088),
                        new ProxyCredentials("user2", "pass2")),
                new ProxyConfigHolderDto(new ProxyNetworkConfig("host1", 8080),
                        new ProxyCredentials("user11", "pass1"))
        );
        assertEquals(proxies, expectationProxies);
    }
}
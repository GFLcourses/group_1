package executor.service.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProxyConfigHolderTest {
    private static ProxyNetworkConfig proxyNetworkConfig = new ProxyNetworkConfig("localhost",4412);
    private static ProxyCredentials proxyCredentials = new ProxyCredentials("login","pass");
    private static ProxyConfigHolderDto proxyConfigHolder;
    private static ProxyConfigHolderDto proxyConfigHolder2;
    private static ProxyConfigHolderDto emptyConfigHolder;
    private static ProxyConfigHolderDto anotherConfigHolder;

    @Before
    public void setUp() {
        proxyConfigHolder = new ProxyConfigHolderDto(proxyNetworkConfig,proxyCredentials);
        proxyConfigHolder2 = new ProxyConfigHolderDto(proxyNetworkConfig,proxyCredentials);
        emptyConfigHolder = new ProxyConfigHolderDto();
        anotherConfigHolder = new ProxyConfigHolderDto(new ProxyNetworkConfig("localhost1",443),
                new ProxyCredentials("login2","pass2"));
    }

    @Test
    public void equalsTest() {
        ProxyConfigHolderDto emptyConfigHolder = new ProxyConfigHolderDto();
        assertEquals(proxyConfigHolder,proxyConfigHolder2);
        assertNotEquals(emptyConfigHolder,proxyConfigHolder);
        assertNotEquals(anotherConfigHolder,proxyConfigHolder);

    }

    @Test
    public void hashCodeTest() {
        assertEquals(proxyConfigHolder.hashCode(),proxyConfigHolder2.hashCode());
        assertNotEquals(emptyConfigHolder.hashCode(),proxyConfigHolder.hashCode());
        assertNotEquals(anotherConfigHolder.hashCode(),proxyConfigHolder.hashCode());
    }

    @Test
    public void setGetProxyNetworkConfigTest() {
        String hostname = "localHost2";
        int port = 441;
        proxyNetworkConfig.setHostname(hostname);
        proxyNetworkConfig.setPort(port);
        proxyConfigHolder.setProxyNetworkConfig(proxyNetworkConfig);

        assertEquals(proxyConfigHolder.getProxyNetworkConfig().getHostname(),hostname);
        assertEquals(proxyConfigHolder.getProxyNetworkConfig().getPort().intValue(),port);
    }

    @Test
    public void setGetProxyCredentialsTest() {
        String username = "login";
        String pass = "new pass";
        proxyCredentials.setUsername(username);
        proxyCredentials.setPassword(pass);
        proxyConfigHolder.setProxyCredentials(proxyCredentials);

        assertEquals(proxyConfigHolder2.getProxyCredentials().getUsername(),username);
        assertEquals(proxyConfigHolder2.getProxyCredentials().getPassword(),pass);

    }


}
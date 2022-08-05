package executor.service.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

class ProxyConfigHolderTest {
    private static ProxyNetworkConfig proxyNetworkConfig = new ProxyNetworkConfig("localhost",4412);
    private static ProxyCredentials proxyCredentials = new ProxyCredentials("login","pass");
    private static ProxyConfigHolder proxyConfigHolder;
    private static ProxyConfigHolder proxyConfigHolder2;

    @Before
    void setUp() {
        proxyConfigHolder = new ProxyConfigHolder(proxyNetworkConfig,proxyCredentials);
        proxyConfigHolder2 = new ProxyConfigHolder(proxyNetworkConfig,proxyCredentials);
    }

    @Test
    public void equalsTest(){
        assertEquals(proxyConfigHolder,proxyConfigHolder2);
    }

    @Test
    public void hashCodeTest(){
        assertEquals(proxyConfigHolder.hashCode(),proxyConfigHolder2.hashCode());
    }

    @Test
    public void setGetProxyNetworkConfigTest(){
        proxyNetworkConfig.setHostname("localHost2");
        proxyNetworkConfig.setPort(441);
        proxyConfigHolder.setProxyNetworkConfig(proxyNetworkConfig);

        assertEquals(proxyConfigHolder.getProxyNetworkConfig().getHostname(),"localHost2");
        assertEquals(Optional.ofNullable(proxyConfigHolder.getProxyNetworkConfig().getPort()),441);
    }

    @Test
    public void setGetProxyCredentialsTest(){
        proxyCredentials.setUsername("login");
        proxyCredentials.setPassword("new pass");
        proxyConfigHolder.setProxyCredentials(proxyCredentials);

        assertEquals(proxyConfigHolder2.getProxyCredentials().getUsername(),"login");
        assertEquals(proxyConfigHolder2.getProxyCredentials().getPassword(),"new pass");

    }


}
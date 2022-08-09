package executor.service.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class ProxyNetworkConfigTest {
    private static ProxyNetworkConfig proxyNetworkConfig;
    private static final String HOSTNAME = "hostname";
    private static final Integer PORT = 8080;
    private static final String NEW_HOSTNAME = "new hostname";
    private static final Integer NEW_PORT = 8081;

    @Before
    public void setup() {
        proxyNetworkConfig = new ProxyNetworkConfig(HOSTNAME, PORT);
    }

    @Test
    public void getHostnameTest() {
        assertEquals(HOSTNAME, proxyNetworkConfig.getHostname());
        assertNotEquals(NEW_HOSTNAME, proxyNetworkConfig.getHostname());
    }

    @Test
    public void getPortTest() {
        assertEquals(PORT, proxyNetworkConfig.getPort());
        assertNotEquals(NEW_PORT, proxyNetworkConfig.getPort());
    }

    @Test
    public void setHostnameTest() {
        proxyNetworkConfig.setHostname(NEW_HOSTNAME);
        assertEquals(NEW_HOSTNAME, proxyNetworkConfig.getHostname());
        assertNotEquals(HOSTNAME, proxyNetworkConfig.getHostname());
    }

    @Test
    public void setPortTest() {
        proxyNetworkConfig.setPort(NEW_PORT);
        assertEquals(NEW_PORT, proxyNetworkConfig.getPort());
        assertNotEquals(PORT, proxyNetworkConfig.getPort());
    }

    @Test
    public void hashCodeTest() {
        int hash = Objects.hash(HOSTNAME, PORT);
        int hashAnother = Objects.hash(NEW_HOSTNAME, NEW_PORT);
        ProxyNetworkConfig empty = new ProxyNetworkConfig();

        assertEquals(hash, proxyNetworkConfig.hashCode());
        assertNotEquals(hashAnother, proxyNetworkConfig.hashCode());
        assertNotEquals(empty.hashCode(), proxyNetworkConfig.hashCode());
        assertNotEquals(empty.hashCode(), null);
        assertEquals(empty.hashCode(), new ProxyNetworkConfig().hashCode());
    }

    @Test
    public void equalsTest() {
        ProxyNetworkConfig pNC = new ProxyNetworkConfig(HOSTNAME, PORT);
        ProxyNetworkConfig pNCAnother = new ProxyNetworkConfig(NEW_HOSTNAME, NEW_PORT);
        ProxyNetworkConfig empty = new ProxyNetworkConfig();

        assertEquals(proxyNetworkConfig, pNC);
        assertNotEquals(proxyNetworkConfig, pNCAnother);
        assertNotEquals(proxyNetworkConfig, null);
        assertNotEquals(empty, proxyNetworkConfig);
        assertNotEquals(empty, null);
        assertEquals(empty, new ProxyNetworkConfig());
    }
}

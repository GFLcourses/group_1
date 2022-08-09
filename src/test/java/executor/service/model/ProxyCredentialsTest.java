package executor.service.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ProxyCredentialsTest {
    private static ProxyCredentials proxyCredentials;
    private static final String USERNAME = "username";
    private static final String NEW_USERNAME = "new username";
    private static final String PASSWORD = "password";
    private static final String NEW_PASSWORD = "new password";

    @Before
    public void setup() {
        proxyCredentials = new ProxyCredentials(USERNAME, PASSWORD);
    }

    @Test
    public void getUsernameTest() {
        assertEquals(USERNAME, proxyCredentials.getUsername());
        assertNotEquals(NEW_USERNAME, proxyCredentials.getUsername());
    }

    @Test
    public void getPasswordTest() {
        assertEquals(PASSWORD, proxyCredentials.getPassword());
        assertNotEquals(NEW_PASSWORD, proxyCredentials.getPassword());
    }

    @Test
    public void setUsernameTest() {
        proxyCredentials.setUsername(NEW_USERNAME);
        assertEquals(NEW_USERNAME, proxyCredentials.getUsername());
        assertNotEquals(USERNAME, proxyCredentials.getUsername());
    }

    @Test
    public void setPasswordTest() {
        proxyCredentials.setPassword(NEW_PASSWORD);
        assertEquals(NEW_PASSWORD, proxyCredentials.getPassword());
        assertNotEquals(PASSWORD, proxyCredentials.getPassword());
    }

    @Test
    public void hashCodeTest() {
        int hash = Objects.hash(USERNAME, PASSWORD);
        int hashAnother = Objects.hash(NEW_USERNAME, NEW_PASSWORD);
        ProxyCredentials empty = new ProxyCredentials();

        assertEquals(hash, proxyCredentials.hashCode());
        assertNotEquals(hashAnother, proxyCredentials.hashCode());
        assertNotEquals(empty.hashCode(), proxyCredentials.hashCode());
        assertNotEquals(empty.hashCode(), null);
        assertEquals(empty.hashCode(), new ProxyCredentials().hashCode());
    }

    @Test
    public void equalsTest() {
        ProxyCredentials pC = new ProxyCredentials(USERNAME, PASSWORD);
        ProxyCredentials pCAnother = new ProxyCredentials(NEW_USERNAME, NEW_PASSWORD);
        ProxyCredentials empty = new ProxyCredentials();

        assertEquals(proxyCredentials, pC);
        assertNotEquals(proxyCredentials, pCAnother);
        assertNotEquals(proxyCredentials, null);
        assertNotEquals(empty, proxyCredentials);
        assertNotEquals(empty, null);
        assertEquals(empty, new ProxyCredentials());
    }
}

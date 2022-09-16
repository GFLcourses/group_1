package executor.service.web_driver;

import executor.model.*;
import executor.service.scenario.ScenarioSourceListenerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ChromeWebDriverInitializerTest {
    private static final String SITE_URL = "http://info.cern.ch";
    private WebDriver webDriver;

    @Before
    public void init() {
        ChromeWebDriverInitializer chromeWebDriverInitializer = ChromeWebDriverInitializer.getInstance();
        ProxyConfigHolder proxyConfigHolder = new ProxyConfigHolder(
                new ProxyNetworkConfig("host1337", 1337),
                new ProxyCredentials("user1337", "password1337")
        );
        webDriver = chromeWebDriverInitializer.initialize();
    }

    @After
    public void closeResources() {
        webDriver.quit();
    }

    @Test
    public void testInitialize() {
        assertNotNull(webDriver);
    }
}
package executor.service.web_driver;

import executor.model.ProxyConfigHolder;

import executor.service.proxy.ProxySourcesClientJson;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ChromeWebDriverInitializerTest {

    @Test
    public void initializeShouldInitializeWebDriverInstance() {
        WebDriver actual = ChromeWebDriverInitializer.getInstance().initialize();

        assertNotNull(actual);
    }

    @Test(expected = NullPointerException.class)
    public void initializeWithProxyConfigArgumentShouldThrowNullPointerExceptionIfProxyConfigIsNull() {
        ProxyConfigHolder proxyConfigHolder = null;
        WebDriver actual = ChromeWebDriverInitializer.getInstance().initialize(proxyConfigHolder);
    }

    @Test
    public void initializeShouldOpenTabWithSpecifiedURL() {

        WebDriver webDriver = ChromeWebDriverInitializer.getInstance().initialize();

        String expected = "data:,";
        String actual = webDriver.getCurrentUrl();

        assertEquals(expected, actual);
    }

    @Test
    public void initializeShouldOpenOneWindow() {

        WebDriver webDriver = ChromeWebDriverInitializer.getInstance().initialize();

        int expected = 1;
        int actual = webDriver.getWindowHandles().size();

        assertEquals(expected, actual);
    }

}
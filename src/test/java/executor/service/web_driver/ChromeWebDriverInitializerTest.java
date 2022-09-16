package executor.service.web_driver;

import executor.service.proxy.ProxySourcesClientJson;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ChromeWebDriverInitializerTest {

    @Test
    public void testInitialize() {
        ProxySourcesClientJson instance = ProxySourcesClientJson.getInstance();
        ChromeWebDriverInitializer chromeWebDriverInitializer = new ChromeWebDriverInitializer();
        WebDriver webDriver = chromeWebDriverInitializer.initialize(instance.getProxy().get());
        System.out.println(webDriver);
    }
}
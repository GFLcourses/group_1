package executor.service.web_driver;

import executor.model.ProxyConfigHolderDto;
import executor.model.ProxyCredentials;
import executor.model.ProxyNetworkConfig;
import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;

public class ChromeWebDriverInitializerTest extends TestCase {

    public void testInitialize() {
        ChromeWebDriverInitializer chromeWebDriverInitializer = ChromeWebDriverInitializer.getInstance();
        ProxyConfigHolderDto proxyConfigHolderDto = new ProxyConfigHolderDto(
                new ProxyNetworkConfig("host1337", 1337),
                new ProxyCredentials("user1337", "password1337")
        );
        WebDriver webDriver = chromeWebDriverInitializer.initialize(proxyConfigHolderDto);
        System.out.println(webDriver);
    }
}
package executor.service.service.web_driver;

import executor.service.model.ProxyConfigHolderDto;
import executor.service.model.ProxyCredentials;
import executor.service.model.ProxyNetworkConfig;
import executor.service.model.WebDriverConfigDTO;
import executor.service.util.PropertiesReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeWebDriverInitializer implements WebDriverInitializer {

    @Override
    public WebDriver initialize(ProxyConfigHolderDto proxyConfigHolder) {
        WebDriverConfigDTO webDriverConfigDTO = PropertiesReader.readWebDriverConfig();
        System.setProperty("webdriver.chrome.driver", webDriverConfigDTO.getWebDriverExecutable());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=" + webDriverConfigDTO.getUserAgent());

        setProxyServer(options, proxyConfigHolder.getProxyNetworkConfig(), proxyConfigHolder.getProxyCredentials());

        return new ChromeDriver(options);
    }

    private void setProxyServer(ChromeOptions options, ProxyNetworkConfig networkConfig, ProxyCredentials credentials) {
        options.addArguments("--proxy-server=" + credentials.getUsername() + ":" + credentials.getPassword() + "@"
                                                + networkConfig.getHostname() + ":" + networkConfig.getPort());
    }
}

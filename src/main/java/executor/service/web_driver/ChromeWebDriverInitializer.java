package executor.service.web_driver;

import executor.model.ProxyCredentials;
import executor.model.ProxyNetworkConfig;
import executor.model.WebDriverConfigDTO;
import executor.model.ProxyConfigHolderDto;
import executor.util.PropertiesReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeWebDriverInitializer implements WebDriverInitializer {
    private static final ChromeWebDriverInitializer INSTANCE = new ChromeWebDriverInitializer();

    protected ChromeWebDriverInitializer() {  }

    static {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    public static ChromeWebDriverInitializer getInstance() {
        return INSTANCE;
    }

    @Override
    public WebDriver initialize(ProxyConfigHolderDto proxyConfigHolder) {
        WebDriverConfigDTO webDriverConfigDTO = PropertiesReader.readWebDriverConfig();
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

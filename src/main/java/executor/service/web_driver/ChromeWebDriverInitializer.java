package executor.service.web_driver;

import executor.model.ProxyCredentials;
import executor.model.ProxyNetworkConfig;
import executor.model.WebDriverConfig;
import executor.model.ProxyConfigHolder;
import executor.util.PropertiesReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ChromeWebDriverInitializer implements WebDriverInitializer {
    @Value("${webdriver.path}")
    private String driverPath;

    @Autowired
    public ChromeWebDriverInitializer() {  }

    @PostConstruct
    public void init() {
        System.setProperty("webdriver.chrome.driver", driverPath);
    }

    @Override
    public WebDriver initialize(ProxyConfigHolder proxyConfigHolder) {
        WebDriverConfig webDriverConfig = PropertiesReader.readWebDriverConfig();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=" + webDriverConfig.getUserAgent());
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920x1080");
        setProxyServer(options, proxyConfigHolder.getProxyNetworkConfig(), proxyConfigHolder.getProxyCredentials());

        return new ChromeDriver(options);
    }

    public synchronized WebDriver initialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--crash-dumps-dir=/tmp");
        options.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }

    private void setProxyServer(ChromeOptions options, ProxyNetworkConfig networkConfig, ProxyCredentials credentials) {
        options.addArguments("--proxy-server=" + credentials.getUsername() + ":" + credentials.getPassword() + "@"
                                                + networkConfig.getHostname() + ":" + networkConfig.getPort());
    }
}

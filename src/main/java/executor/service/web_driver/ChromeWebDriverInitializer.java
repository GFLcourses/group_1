package executor.service.web_driver;

import executor.model.ProxyCredentials;
import executor.model.ProxyNetworkConfig;
import executor.model.WebDriverConfig;
import executor.model.ProxyConfigHolder;
import executor.util.PropertiesReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeWebDriverInitializer implements WebDriverInitializer {
    private static final ChromeWebDriverInitializer INSTANCE = new ChromeWebDriverInitializer();

    protected ChromeWebDriverInitializer() {  }

    static {
        System.setProperty("webdriver.chrome.driver", "/home/ubuntu/staff/chromedriver");
    }

    public static ChromeWebDriverInitializer getInstance() {
        return INSTANCE;
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

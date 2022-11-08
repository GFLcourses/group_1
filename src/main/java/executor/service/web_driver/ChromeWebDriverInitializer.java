package executor.service.web_driver;

import executor.model.ProxyConfigHolder;

import org.openqa.selenium.Proxy;
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
        var host = proxyConfigHolder.getProxyNetworkConfig().getHostname();
        var port = proxyConfigHolder.getProxyNetworkConfig().getPort();

        Proxy proxy = new Proxy();
        proxy.setHttpProxy(String.format("<%s:%x>", host, port));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--crash-dumps-dir=/tmp");
        options.addArguments("--disable-dev-shm-usage");
        options.setCapability("proxy", proxy);

        return new ChromeDriver(options);
    }

    public WebDriver initialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--crash-dumps-dir=/tmp");
        options.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }
}

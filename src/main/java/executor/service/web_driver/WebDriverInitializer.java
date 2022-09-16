package executor.service.web_driver;

import executor.model.ProxyConfigHolder;
import org.openqa.selenium.WebDriver;

public interface WebDriverInitializer {

    WebDriver initialize(ProxyConfigHolder proxyConfigHolder);
}

package executor.service.web_driver;

import executor.exception.ChromeDriverFailedInitializationException;
import executor.model.ProxyConfigHolderDto;
import org.openqa.selenium.WebDriver;

public interface WebDriverInitializer {

    WebDriver initialize(ProxyConfigHolderDto proxyConfigHolder) throws ChromeDriverFailedInitializationException;
}

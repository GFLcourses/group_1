package executor.service.service.web_driver;

import executor.service.model.ProxyConfigHolderDto;
import org.openqa.selenium.WebDriver;

public interface WebDriverInitializer {
    WebDriver initialize(ProxyConfigHolderDto proxyConfigHolderDto);
}

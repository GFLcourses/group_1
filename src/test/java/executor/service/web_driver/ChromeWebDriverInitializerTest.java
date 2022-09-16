package executor.service.web_driver;

import executor.model.ProxyConfigHolder;
import executor.model.ProxyCredentials;
import executor.model.ProxyNetworkConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ChromeWebDriverInitializerTest {
    private static final String SITE_URL = "http://info.cern.ch";
    private WebDriver webDriver;

    @Before
    public void init() {
        ChromeWebDriverInitializer chromeWebDriverInitializer = ChromeWebDriverInitializer.getInstance();
        ProxyConfigHolder proxyConfigHolder = new ProxyConfigHolder(
                new ProxyNetworkConfig("host1337", 1337),
                new ProxyCredentials("user1337", "password1337")
        );
        webDriver = chromeWebDriverInitializer.initialize();
    }

    @After
    public void closeResources() {
        webDriver.quit();
    }

    @Test
    public void testInitialize() {
        assertNotNull(webDriver);
    }

    @Test
    public void testElementsSearching() {
        webDriver.get("http://info.cern.ch");
        List<WebElement> webElements = webDriver.findElements(By.cssSelector("body > ul > li > a"));
        assertEquals(4, webElements.size());
        WebElement firstLink = webElements.get(0);
        firstLink.click();
    }

    @Test
    public void testInput() throws InterruptedException {
        webDriver.get("https://google.com");
        WebElement searchInput = webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
        TimeUnit.SECONDS.sleep(5L);
        searchInput.sendKeys("selenium webdriver download");
        TimeUnit.SECONDS.sleep(5L);
        searchInput.sendKeys(Keys.ENTER);
    }
}
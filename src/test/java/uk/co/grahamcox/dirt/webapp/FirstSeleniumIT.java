package uk.co.grahamcox.dirt.webapp;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Created by graham on 10/06/15.
 */
public class FirstSeleniumIT {
    private static final Map<String, Supplier<WebDriver>> DRIVERS = new HashMap<>();

    static {
        DRIVERS.put("phantomjs", PhantomJSDriver::new);
        DRIVERS.put("firefox", FirefoxDriver::new);
        DRIVERS.put("chrome", ChromeDriver::new);
    }

    @Test
    public void test() throws InterruptedException {
        String dirtUrl = System.getProperty("test.url");
        String driverName = System.getProperty("webdriver", "phantomjs").toLowerCase();
        WebDriver driver = Optional.ofNullable(DRIVERS.get(driverName))
            .map(Supplier::get)
            .orElseThrow(() -> new IllegalArgumentException("Unsupported WebDriver: " + driverName));

        driver.get(dirtUrl);
        TimeUnit.SECONDS.sleep(2);

        WebElement username = driver.findElement(By.cssSelector(".test-loginform .test-username"));
        WebElement password = driver.findElement(By.cssSelector(".test-loginform .test-password"));
        Assertions.assertThat(username.isDisplayed())
            .isTrue();
        Assertions.assertThat(password.isDisplayed())
            .isTrue();

        driver.quit();
    }
}

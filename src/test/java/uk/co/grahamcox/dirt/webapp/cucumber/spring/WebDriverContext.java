package uk.co.grahamcox.dirt.webapp.cucumber.spring;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Spring Configuration for managing the Web Driver
 */
@Configuration
public class WebDriverContext {
    private static final Map<String, Supplier<WebDriver>> DRIVERS = new HashMap<>();

    static {
        DRIVERS.put("phantomjs", PhantomJSDriver::new);
        DRIVERS.put("firefox", FirefoxDriver::new);
        DRIVERS.put("chrome", ChromeDriver::new);
    }

    /**
     * Get the WebDriver bean to use
     * @return the WebDriver
     */
    @Bean(name = "webdriver", destroyMethod = "quit")
    @Scope("cucumber-glue")
    public WebDriver getWebDriver() {
        String driverName = System.getProperty("webdriver", "phantomjs").toLowerCase();
        WebDriver driver = Optional.ofNullable(DRIVERS.get(driverName))
            .map(Supplier::get)
            .orElseThrow(() -> new IllegalArgumentException("Unsupported WebDriver: " + driverName));


        return driver;
    }
}

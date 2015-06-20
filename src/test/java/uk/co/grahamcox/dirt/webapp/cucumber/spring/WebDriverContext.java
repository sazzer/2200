package uk.co.grahamcox.dirt.webapp.cucumber.spring;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import uk.co.grahamcox.dirt.webapp.pagemodels.MainPageModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Spring Configuration for managing the Web Driver
 */
@Configuration
public class WebDriverContext {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverContext.class);

    /** Map of the supported web drivers */
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
        LOG.info("Creating Web Driver for driver {}", driverName);
        WebDriver driver = Optional.ofNullable(DRIVERS.get(driverName))
            .map(Supplier::get)
            .orElseThrow(() -> new IllegalArgumentException("Unsupported WebDriver: " + driverName));

        LOG.info("Created Web Driver: {}", driver);
        return driver;
    }

    /**
     * Get the page model for the main page
     * @return the page model
     */
    @Bean(name = "mainPageModel")
    @Scope("cucumber-glue")
    public MainPageModel getMainPage() {
        return new MainPageModel(getWebDriver());
    }
}

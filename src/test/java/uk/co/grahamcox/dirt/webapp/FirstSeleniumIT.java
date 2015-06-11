package uk.co.grahamcox.dirt.webapp;

import java.util.concurrent.TimeUnit;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by graham on 10/06/15.
 */
public class FirstSeleniumIT {
    @Test
    public void test() throws InterruptedException {
        String dirtUrl = System.getProperty("test.url");
        String binary = System.getProperty("phantomjs.binary");
        DesiredCapabilities DesireCaps = new DesiredCapabilities();
        DesireCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, binary);
        WebDriver driver=new PhantomJSDriver(DesireCaps);

        driver.get(dirtUrl);
        TimeUnit.SECONDS.sleep(2);

        WebElement homepage = driver.findElement(By.id("homepage"));
        Assertions.assertThat(homepage.getText())
            .isNotNull()
            .isNotEmpty()
            .isEqualTo("Hello, Graham");
    }
}

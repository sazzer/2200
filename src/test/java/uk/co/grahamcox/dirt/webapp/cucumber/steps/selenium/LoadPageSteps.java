package uk.co.grahamcox.dirt.webapp.cucumber.steps.selenium;

import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Cucumber Steps to load the home page
 */
@Configuration
public class LoadPageSteps {
    /** The Web Driver to use */
    @Autowired
    private WebDriver webDriver;

    /**
     * Visit the home page
     */
    @Given("that I visit the home page")
    public void loadHomePage() {
        String dirtUrl = System.getProperty("test.url");
        webDriver.get(dirtUrl);
    }
}

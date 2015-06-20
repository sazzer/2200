package uk.co.grahamcox.dirt.webapp.pagemodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

/**
 * Page Model representing the main page
 */
public class MainPageModel {
    /** The web driver for the browser */
    private final WebDriver webDriver;

    /**
     * Construct the page model
     * @param webDriver the web driver
     */
    public MainPageModel(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Get the login form from the main page, if it's present
     * @return the login form
     */
    public Optional<LoginFormPageModel> getLoginForm() {
        WebElement loginFormElement = webDriver.findElement(By.cssSelector(".test-loginform"));
        return Optional.of(loginFormElement)
            .filter(WebElement::isDisplayed)
            .map(LoginFormPageModel::new);
    }
}

package uk.co.grahamcox.dirt.webapp.cucumber.steps.selenium;

import cucumber.api.java.en.Then;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Steps for managing logging in and out
 */
public class LoginSteps {
    /** The Web Driver to use */
    @Autowired
    private WebDriver webDriver;

    /**
     * Check that the user isn't logged in
     */
    @Then("I am not logged in")
    public void checkNotLoggedIn() {
        WebElement username = webDriver.findElement(By.cssSelector(".test-loginform .test-username"));
        WebElement password = webDriver.findElement(By.cssSelector(".test-loginform .test-password"));
        Assertions.assertThat(username.isDisplayed())
            .isTrue();
        Assertions.assertThat(password.isDisplayed())
            .isTrue();
    }
}

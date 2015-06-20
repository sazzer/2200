package uk.co.grahamcox.dirt.webapp.pagemodels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Page Model for the Login form
 */
public class LoginFormPageModel {
    /** The login form element */
    private final WebElement loginFormElement;

    /**
     * Construct the login form page model
     * @param loginFormElement the element representing the login form
     */
    public LoginFormPageModel(WebElement loginFormElement) {
        this.loginFormElement = loginFormElement;
    }

    /**
     * Enter the given username in the login form
     * @param username the username
     */
    public void enterUsername(String username) {
        loginFormElement.findElement(By.className("test-username"))
            .sendKeys(username);
    }

    /**
     * Enter the given password in the login form
     * @param password the password
     */
    public void enterPassword(String password) {
        loginFormElement.findElement(By.className("test-password"))
            .sendKeys(password);
    }

    /**
     * Press the Login button on the login form
     */
    public void login() {
        loginFormElement.findElement(By.className("test-loginbutton"))
            .click();
    }
}


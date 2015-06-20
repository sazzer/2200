package uk.co.grahamcox.dirt.webapp.pagemodels;

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
}


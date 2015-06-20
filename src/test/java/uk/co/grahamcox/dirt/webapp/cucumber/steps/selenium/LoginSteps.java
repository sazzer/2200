package uk.co.grahamcox.dirt.webapp.cucumber.steps.selenium;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import uk.co.grahamcox.dirt.webapp.pagemodels.LoginFormPageModel;
import uk.co.grahamcox.dirt.webapp.pagemodels.MainPageModel;

/**
 * Steps for managing logging in and out
 */
public class LoginSteps {
    /** The Page Model for the main page */
    @Autowired
    private MainPageModel mainPageModel;

    @When("^I log in as user \"(.*)\" with password \"(.*)\"$")
    public void login(String username, String password) {
        checkNotLoggedIn();
        mainPageModel.getLoginForm();
        mainPageModel.getLoginForm().ifPresent(loginForm -> {
            loginForm.enterUsername(username);
            loginForm.enterPassword(password);
            loginForm.login();
        });
    }

    /**
     * Check that the user isn't logged in
     */
    @Then("^I am not logged in$")
    public void checkNotLoggedIn() {
        Assertions.assertThat(mainPageModel.getLoginForm())
            .isPresent();
    }

    /**
     * Check that the specified login error is present
     * @param error the login error to look for
     */
    @Then("^there is a login error of \"(.*)\"$")
    public void checkMatchingLoginError(String error) {
        Assertions.assertThat(mainPageModel.getLoginForm().get().getLoginErrors())
            .contains(error);
    }
}

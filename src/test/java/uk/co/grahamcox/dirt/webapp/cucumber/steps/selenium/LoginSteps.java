package uk.co.grahamcox.dirt.webapp.cucumber.steps.selenium;

import cucumber.api.java.en.Then;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import uk.co.grahamcox.dirt.webapp.pagemodels.MainPageModel;

/**
 * Steps for managing logging in and out
 */
public class LoginSteps {
    /** The Page Model for the main page */
    @Autowired
    private MainPageModel mainPageModel;

    /**
     * Check that the user isn't logged in
     */
    @Then("I am not logged in")
    public void checkNotLoggedIn() {
        Assertions.assertThat(mainPageModel.getLoginForm().isPresent())
            .isTrue();
    }
}

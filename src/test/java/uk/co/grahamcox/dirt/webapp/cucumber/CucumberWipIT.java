package uk.co.grahamcox.dirt.webapp.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Integration Test runner to run all of the WIP Cucumber Tests
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    format = {
        "pretty"
    },
    features = "classpath:uk/co/grahamcox/dirt/webapp/cucumber/features",
    glue = "uk.co.grahamcox.dirt.webapp.cucumber.steps",
    tags = {"@wip"},
    strict = false
)
public class CucumberWipIT {
}

package uk.co.grahamcox.dirt.webapp.cucumber.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Root Spring Context for all of the Cucumber tests
 */
@Configuration
@Import({
    WebDriverContext.class
})
public class CucumberContext {
}

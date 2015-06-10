package uk.co.grahamcox.dirt.webapp;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by graham on 10/06/15.
 */
public class FirstSeleniumIT {
    @Test
    public void test() {
        String dirtUrl = System.getProperty("dirtUrl");
        System.out.println(dirtUrl);
        Assertions.assertThat(dirtUrl)
            .isNotNull()
            .isNotEmpty()
            .startsWith("http://localhost");
    }
}

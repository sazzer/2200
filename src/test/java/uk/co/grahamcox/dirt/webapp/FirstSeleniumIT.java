package uk.co.grahamcox.dirt.webapp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by graham on 10/06/15.
 */
public class FirstSeleniumIT {
    @Test
    public void test() {
        String dirtUrl = System.getProperty("dirtUrl");
        System.out.println(dirtUrl);
        Assert.assertNotNull(dirtUrl);
    }
}

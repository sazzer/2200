package uk.co.grahamcox.dirt.network;

import org.junit.Test;
import uk.co.grahamcox.dirt.network.telnet.netty.TelnetServer;

/**
 * Created by graham on 30/05/15.
 */
public class NettyTestRig {
    @Test
    public void test() throws Exception {
        TelnetServer server = new TelnetServer((short)12345);
        server.run();
    }
}

package uk.co.grahamcox.dirt.network.telnet.encoder;

import org.junit.Assert;
import org.junit.Before;
import uk.co.grahamcox.dirt.network.telnet.TelnetMessage;

import java.util.Optional;

/**
 * Unit tests for the Telnet Message Decoder
 */
public class TelnetMessageDecoderTest extends TelnetMessageSerializationTestBase {
    /** The decoder to test */
    private TelnetMessageDecoder decoder;

    /**
     * Set up a clean Decoder for every test
     */
    @Before
    public void setup() {
        decoder = new TelnetMessageDecoder();
    }

    /**
     * Assert that the given bytes eventually decode to the expected message
     * @param expected the expected message
     * @param bytes the bytes to decode
     */
    protected void assertMessage(TelnetMessage expected, byte... bytes) {
        // All of these produce no message
        for (int i = 0; i < bytes.length - 1; ++i) {
            Assert.assertEquals(Optional.empty(), decoder.inject(bytes[i]));
        }

        // This produces the expected message
        Assert.assertEquals(Optional.of(expected), decoder.inject(bytes[bytes.length - 1]));
    }
}

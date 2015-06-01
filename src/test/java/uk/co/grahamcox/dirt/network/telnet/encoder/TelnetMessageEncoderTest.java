package uk.co.grahamcox.dirt.network.telnet.encoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.grahamcox.dirt.network.telnet.TelnetMessage;

/**
 * Unit tests for the Telnet Message Encoder
 */
public class TelnetMessageEncoderTest extends TelnetMessageSerializationTestBase {
    /** The encoder to test */
    private TelnetMessageEncoder encoder;

    /**
     * Set up a clean encoder for every test, to ensure there is no contamination between tests
     */
    @Before
    public void setup() {
        encoder = new TelnetMessageEncoder();
    }

    /**
     * Test that when we encode a message that we don't support that we get a valid exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEncodeUnknownMessage() {
        encoder.encode(new TelnetMessage() {
        });
    }

    /**
     * Assert that the given message encodes to the given bytes
     * @param message the message to encode
     * @param expected the bytes to expect
     */
    protected void assertMessage(TelnetMessage message, byte... expected) {
        byte[] bytes = encoder.encode(message);
        Assert.assertArrayEquals(expected, bytes);
    }
}

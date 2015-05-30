package uk.co.grahamcox.dirt.network.telnet.encoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.grahamcox.dirt.network.telnet.ByteMessage;
import uk.co.grahamcox.dirt.network.telnet.TelnetMessage;

/**
 * Unit tests for the Telnet Message Encoder
 */
public class TelnetMessageEncoderTest {
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
     * Test that when we encode any simple ByteMessage - i.e. anything that's not an IAC - then we get a single byte out
     */
    @Test
    public void testEncodeSimpleByte() {
        for (int i = 0; i < 0xff; ++i) {
            byte b = (byte)i;

            ByteMessage byteMessage = new ByteMessage(b);
            byte[] bytes = encoder.encode(byteMessage);
            Assert.assertArrayEquals(new byte[]{b}, bytes);
        }
    }

    /**
     * Test that when we encode a ByteMessage containing an IAC then it is escaped correctly
     */
    @Test
    public void testEncodeIACByte() {
        ByteMessage byteMessage = new ByteMessage((byte)0xff);
        byte[] bytes = encoder.encode(byteMessage);
        Assert.assertArrayEquals(new byte[]{(byte)0xff, (byte)0xff}, bytes);
    }
}

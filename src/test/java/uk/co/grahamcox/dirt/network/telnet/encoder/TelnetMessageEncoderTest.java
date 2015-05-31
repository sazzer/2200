package uk.co.grahamcox.dirt.network.telnet.encoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.grahamcox.dirt.network.telnet.ByteMessage;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiation;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiationMessage;
import uk.co.grahamcox.dirt.network.telnet.TelnetMessage;

import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        getNonIacBytes().forEach(b -> {
            ByteMessage byteMessage = new ByteMessage(b);
            assertMessage(byteMessage, b);
        });
    }

    /**
     * Test that when we encode a ByteMessage containing an IAC then it is escaped correctly
     */
    @Test
    public void testEncodeIACByte() {
        ByteMessage byteMessage = new ByteMessage(TelnetBytes.IAC);
        assertMessage(byteMessage, TelnetBytes.IAC, TelnetBytes.IAC);
    }

    /**
     * Test that when we encode a Negotiation DO for any ID that is not an IAC then we get the correct bytes out
     * The correct bytes are IAC DO <id>
     */
    @Test
    public void testEncodeSimpleNegotiationDo() {
        getNonIacBytes().forEach(b -> {
            OptionNegotiationMessage message = new OptionNegotiationMessage(OptionNegotiation.DO, b);
            assertMessage(message, TelnetBytes.IAC, TelnetBytes.DO, b);
        });
    }

    /**
     * Test that when we encode a Negotiation DO for an ID of IAC then we get the correct bytes out
     * The correct bytes are IAC DO IAC IAC
     */
    @Test
    public void testEncodeIacNegotiationDo() {
        OptionNegotiationMessage message = new OptionNegotiationMessage(OptionNegotiation.DO, TelnetBytes.IAC);
        assertMessage(message, TelnetBytes.IAC, TelnetBytes.DO, TelnetBytes.IAC, TelnetBytes.IAC);
    }

    /**
     * Test that when we encode a Negotiation DONT for any ID that is not an IAC then we get the correct bytes out
     * The correct bytes are IAC DONT <id>
     */
    @Test
    public void testEncodeSimpleNegotiationDont() {
        getNonIacBytes().forEach(b -> {
            OptionNegotiationMessage message = new OptionNegotiationMessage(OptionNegotiation.DONT, b);
            assertMessage(message, TelnetBytes.IAC, TelnetBytes.DONT, b);
        });
    }

    /**
     * Test that when we encode a Negotiation DONT for an ID of IAC then we get the correct bytes out
     * The correct bytes are IAC DONT IAC IAC
     */
    @Test
    public void testEncodeIacNegotiationDont() {
        OptionNegotiationMessage message = new OptionNegotiationMessage(OptionNegotiation.DONT, TelnetBytes.IAC);
        assertMessage(message, TelnetBytes.IAC, TelnetBytes.DONT, TelnetBytes.IAC, TelnetBytes.IAC);
    }

    /**
     * Test that when we encode a Negotiation WILL for any ID that is not an IAC then we get the correct bytes out
     * The correct bytes are IAC WILL <id>
     */
    @Test
    public void testEncodeSimpleNegotiationWill() {
        getNonIacBytes().forEach(b -> {
            OptionNegotiationMessage message = new OptionNegotiationMessage(OptionNegotiation.WILL, b);
            assertMessage(message, TelnetBytes.IAC, TelnetBytes.WILL, b);
        });
    }

    /**
     * Test that when we encode a Negotiation WILL for an ID of IAC then we get the correct bytes out
     * The correct bytes are IAC WILL IAC IAC
     */
    @Test
    public void testEncodeIacNegotiationWill() {
        OptionNegotiationMessage message = new OptionNegotiationMessage(OptionNegotiation.WILL, TelnetBytes.IAC);
        assertMessage(message, TelnetBytes.IAC, TelnetBytes.WILL, TelnetBytes.IAC, TelnetBytes.IAC);
    }

    /**
     * Test that when we encode a Negotiation WONT for any ID that is not an IAC then we get the correct bytes out
     * The correct bytes are IAC WONT <id>
     */
    @Test
    public void testEncodeSimpleNegotiationWont() {
        getNonIacBytes().forEach(b -> {
            OptionNegotiationMessage message = new OptionNegotiationMessage(OptionNegotiation.WONT, b);
            assertMessage(message, TelnetBytes.IAC, TelnetBytes.WONT, b);
        });
    }

    /**
     * Test that when we encode a Negotiation WONT for an ID of IAC then we get the correct bytes out
     * The correct bytes are IAC WONT IAC IAC
     */
    @Test
    public void testEncodeIacNegotiationWont() {
        OptionNegotiationMessage message = new OptionNegotiationMessage(OptionNegotiation.WONT, TelnetBytes.IAC);
        assertMessage(message, TelnetBytes.IAC, TelnetBytes.WONT, TelnetBytes.IAC, TelnetBytes.IAC);
    }

    /**
     * Generate a stream of all the bytes that are not the IAC byte
     * @return the bytes
     */
    private Stream<Byte> getNonIacBytes() {
        return IntStream.range(0, 0xff)
            .mapToObj(i -> (byte)i);
    }

    /**
     * Assert that the given message encodes to the given bytes
     * @param message the message to encode
     * @param expected the bytes to expect
     */
    private void assertMessage(TelnetMessage message, int... expected) {
        byte[] expectedBytes = new byte[expected.length];
        for (int i = 0; i < expected.length; ++i) {
            expectedBytes[i] = (byte)expected[i];
        }
        assertMessage(message, expectedBytes);
    }

    /**
     * Assert that the given message encodes to the given bytes
     * @param message the message to encode
     * @param expected the bytes to expect
     */
    private void assertMessage(TelnetMessage message, byte... expected) {
        byte[] bytes = encoder.encode(message);
        Assert.assertArrayEquals(expected, bytes);
    }
}

package uk.co.grahamcox.dirt.network.telnet.encoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.grahamcox.dirt.network.telnet.AbortOutputMessage;
import uk.co.grahamcox.dirt.network.telnet.AreYouThereMessage;
import uk.co.grahamcox.dirt.network.telnet.BreakMessage;
import uk.co.grahamcox.dirt.network.telnet.ByteMessage;
import uk.co.grahamcox.dirt.network.telnet.DataMarkMessage;
import uk.co.grahamcox.dirt.network.telnet.EraseCharacterMessage;
import uk.co.grahamcox.dirt.network.telnet.EraseLineMessage;
import uk.co.grahamcox.dirt.network.telnet.GoAheadMessage;
import uk.co.grahamcox.dirt.network.telnet.InterruptMessage;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiation;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiationMessage;
import uk.co.grahamcox.dirt.network.telnet.OptionSubNegotiationMessage;
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
     * Test that when we encode a SubNegotiation for an ID that isn't IAC and has a payload that contains a single
     * byte that isn't IAC then we get the correct bytes out
     * The correct bytes are IAC SB <id> <payload> IAC SE
     */
    @Test
    public void testEncodeSubnegotiationSimpleIdSimplePayload() {
        getNonIacBytes().forEach(id -> {
            getNonIacBytes().forEach(payload -> {
                OptionSubNegotiationMessage message = new OptionSubNegotiationMessage(id, new byte[]{payload});
                assertMessage(message,
                    TelnetBytes.IAC, TelnetBytes.SB, id, payload, TelnetBytes.IAC, TelnetBytes.SE);
            });
        });
    }

    /**
     * Test that when we encode a SubNegotiation for an ID that is IAC and has a payload that contains a single
     * byte that isn't IAC then we get the correct bytes out
     * The correct bytes are IAC SB IAC <id> <payload> IAC SE
     */
    @Test
    public void testEncodeSubnegotiationIacIdSimplePayload() {
        getNonIacBytes().forEach(payload -> {
            OptionSubNegotiationMessage message = new OptionSubNegotiationMessage(TelnetBytes.IAC, new byte[]{payload});
            assertMessage(message,
                TelnetBytes.IAC, TelnetBytes.SB,
                TelnetBytes.IAC, TelnetBytes.IAC,
                payload,
                TelnetBytes.IAC, TelnetBytes.SE);
        });
    }

    /**
     * Test that when we encode a SubNegotiation for an ID that isn't IAC and has a payload that contains a single
     * byte that is IAC then we get the correct bytes out
     * The correct bytes are IAC SB <id> IAC <payload> IAC SE
     */
    @Test
    public void testEncodeSubnegotiationSimpleIdIACPayload() {
        getNonIacBytes().forEach(id -> {
            OptionSubNegotiationMessage message = new OptionSubNegotiationMessage(id, new byte[]{TelnetBytes.IAC});
            assertMessage(message,
                TelnetBytes.IAC, TelnetBytes.SB,
                id,
                TelnetBytes.IAC, TelnetBytes.IAC,
                TelnetBytes.IAC, TelnetBytes.SE);
        });
    }

    /**
     * Test that all of the other messages - Break, GoAhead, etc - work correctly
     */
    @Test
    public void testOtherMessages() {
        assertMessage(new DataMarkMessage(), TelnetBytes.IAC, TelnetBytes.DATA_MARK);
        assertMessage(new BreakMessage(), TelnetBytes.IAC, TelnetBytes.BREAK);
        assertMessage(new InterruptMessage(), TelnetBytes.IAC, TelnetBytes.INTERRUPT);
        assertMessage(new AbortOutputMessage(), TelnetBytes.IAC, TelnetBytes.ABORT_OUTPUT);
        assertMessage(new AreYouThereMessage(), TelnetBytes.IAC, TelnetBytes.ARE_YOU_THERE);
        assertMessage(new EraseCharacterMessage(), TelnetBytes.IAC, TelnetBytes.ERASE_CHARACTER);
        assertMessage(new EraseLineMessage(), TelnetBytes.IAC, TelnetBytes.ERASE_LINE);
        assertMessage(new GoAheadMessage(), TelnetBytes.IAC, TelnetBytes.GO_AHEAD);
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

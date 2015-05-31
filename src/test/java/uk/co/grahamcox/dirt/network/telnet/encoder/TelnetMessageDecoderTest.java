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
import uk.co.grahamcox.dirt.network.telnet.TelnetMessage;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Unit tests for the Telnet Message Decoder
 */
public class TelnetMessageDecoderTest {
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
     * Test decoding a simple byte - that is any single byte that is not an IAC
     */
    @Test
    public void testDecodeSimpleByte() {
        getNonIacBytes().forEach(b -> {
            Optional<TelnetMessage> message = decoder.inject(b);
            Assert.assertEquals(Optional.of(new ByteMessage(b)), message);
        });
    }

    /**
     * Test decoding a single byte that is an escaped IAC
     */
    @Test
    public void testDecodeEscapedIAC() {
        assertDecodeBytes(new ByteMessage(TelnetBytes.IAC), TelnetBytes.IAC, TelnetBytes.IAC);
    }

    /**
     * Test decoding an option DO negotiation where the ID is not an IAC
     */
    @Test
    public void testDecodeSimpleNegotiateDo() {
        getNonIacBytes().forEach(b ->
            assertDecodeBytes(new OptionNegotiationMessage(OptionNegotiation.DO, b),
                TelnetBytes.IAC, TelnetBytes.DO, b));
    }

    /**
     * Test decoding an option DO negotiation where the ID is an IAC
     */
    @Test
    public void testDecodeNegotiateDoIAC() {
        assertDecodeBytes(new OptionNegotiationMessage(OptionNegotiation.DO, TelnetBytes.IAC),
            TelnetBytes.IAC, TelnetBytes.DO, TelnetBytes.IAC, TelnetBytes.IAC);
    }

    /**
     * Test decoding an option DONT negotiation where the ID is not an IAC
     */
    @Test
    public void testDecodeSimpleNegotiateDont() {
        getNonIacBytes().forEach(b ->
            assertDecodeBytes(new OptionNegotiationMessage(OptionNegotiation.DONT, b),
                TelnetBytes.IAC, TelnetBytes.DONT, b));
    }

    /**
     * Test decoding an option DONT negotiation where the ID is an IAC
     */
    @Test
    public void testDecodeNegotiateDontIAC() {
        assertDecodeBytes(new OptionNegotiationMessage(OptionNegotiation.DONT, TelnetBytes.IAC),
            TelnetBytes.IAC, TelnetBytes.DONT, TelnetBytes.IAC, TelnetBytes.IAC);
    }

    /**
     * Test decoding an option WILL negotiation where the ID is not an IAC
     */
    @Test
    public void testDecodeSimpleNegotiateWill() {
        getNonIacBytes().forEach(b ->
            assertDecodeBytes(new OptionNegotiationMessage(OptionNegotiation.WILL, b),
                TelnetBytes.IAC, TelnetBytes.WILL, b));
    }

    /**
     * Test decoding an option WILL negotiation where the ID is an IAC
     */
    @Test
    public void testDecodeNegotiateWillIAC() {
        assertDecodeBytes(new OptionNegotiationMessage(OptionNegotiation.WILL, TelnetBytes.IAC),
            TelnetBytes.IAC, TelnetBytes.WILL, TelnetBytes.IAC, TelnetBytes.IAC);
    }

    /**
     * Test decoding an option WONT negotiation where the ID is not an IAC
     */
    @Test
    public void testDecodeSimpleNegotiateWont() {
        getNonIacBytes().forEach(b ->
            assertDecodeBytes(new OptionNegotiationMessage(OptionNegotiation.WONT, b),
                TelnetBytes.IAC, TelnetBytes.WONT, b));
    }

    /**
     * Test decoding an option WONT negotiation where the ID is an IAC
     */
    @Test
    public void testDecodeNegotiateWontIAC() {
        assertDecodeBytes(new OptionNegotiationMessage(OptionNegotiation.WONT, TelnetBytes.IAC),
            TelnetBytes.IAC, TelnetBytes.WONT, TelnetBytes.IAC, TelnetBytes.IAC);
    }

    /**
     * Test that all of the other messages - Break, GoAhead, etc - work correctly
     */
    @Test
    public void testOtherMessages() {
        assertDecodeBytes(new DataMarkMessage(), TelnetBytes.IAC, TelnetBytes.DATA_MARK);
        assertDecodeBytes(new BreakMessage(), TelnetBytes.IAC, TelnetBytes.BREAK);
        assertDecodeBytes(new InterruptMessage(), TelnetBytes.IAC, TelnetBytes.INTERRUPT);
        assertDecodeBytes(new AbortOutputMessage(), TelnetBytes.IAC, TelnetBytes.ABORT_OUTPUT);
        assertDecodeBytes(new AreYouThereMessage(), TelnetBytes.IAC, TelnetBytes.ARE_YOU_THERE);
        assertDecodeBytes(new EraseCharacterMessage(), TelnetBytes.IAC, TelnetBytes.ERASE_CHARACTER);
        assertDecodeBytes(new EraseLineMessage(), TelnetBytes.IAC, TelnetBytes.ERASE_LINE);
        assertDecodeBytes(new GoAheadMessage(), TelnetBytes.IAC, TelnetBytes.GO_AHEAD);
    }

    /**
     * Assert that the given bytes eventually decode to the expected message
     * @param expected the expected message
     * @param bytes the bytes to decode
     */
    private void assertDecodeBytes(TelnetMessage expected, byte... bytes) {
        // All of these produce no message
        for (int i = 0; i < bytes.length - 1; ++i) {
            Assert.assertEquals(Optional.empty(), decoder.inject(bytes[i]));
        }

        // This produces the expected message
        Assert.assertEquals(Optional.of(expected), decoder.inject(bytes[bytes.length - 1]));
    }

    /**
     * Generate a stream of all the bytes that are not the IAC byte
     * @return the bytes
     */
    private Stream<Byte> getNonIacBytes() {
        return IntStream.range(0, 0xff)
            .mapToObj(i -> (byte)i);
    }
}

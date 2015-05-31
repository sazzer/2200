package uk.co.grahamcox.dirt.network.telnet.encoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.grahamcox.dirt.network.telnet.ByteMessage;
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
        Assert.assertEquals(Optional.empty(), decoder.inject(TelnetBytes.IAC));
        Assert.assertEquals(Optional.of(new ByteMessage(TelnetBytes.IAC)), decoder.inject(TelnetBytes.IAC));
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

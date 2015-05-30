package uk.co.grahamcox.dirt.network.telnet.encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.grahamcox.dirt.network.telnet.ByteMessage;
import uk.co.grahamcox.dirt.network.telnet.TelnetMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Helper to encode a Telnet Message into a set of bytes
 */
public final class TelnetMessageEncoder {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(TelnetMessageEncoder.class);

    /** Byte representing the IAC */
    private static final byte IAC = (byte) 0xff;

    /** The map of encoders to use */
    private final Map<Class<? extends TelnetMessage>, Function<TelnetMessage, byte[]>> encoders;

    /**
     * Construct the encoder
     */
    public TelnetMessageEncoder() {
        encoders = new HashMap<>();

        encoders.put(ByteMessage.class, this::encodeByteMessage);
    }

    /**
     * Encode the given message into the appropriate bytes
     * @param message the message to encode
     * @return the bytes representing the message
     * @throws IllegalArgumentException if the provided message is not supported
     */
    public byte[] encode(final TelnetMessage message) {
        return Optional.ofNullable(encoders.get(message.getClass()))
            .map(encoder -> {
                LOG.debug("Encoding message {} with encoder {}", message, encoder);
                return encoder.apply(message);
            })
            .orElseThrow(() -> {
                LOG.error("Requested to encode unknown message class {}", message.getClass());
                return new IllegalArgumentException("No encoder found for message of type: " + message.getClass());
            });
    }

    /**
     * Encode the given ByteMessage to the appropriate bytes
     * @param telnetMessage the ByteMessage to encode
     * @return the bytes
     */
    private byte[] encodeByteMessage(final TelnetMessage telnetMessage) {
        ByteMessage byteMessage = (ByteMessage)telnetMessage;
        byte[] result;

        if (byteMessage.getValue() == IAC) {
            result = new byte[]{IAC, byteMessage.getValue()};
        } else {
            result = new byte[]{byteMessage.getValue()};
        }

        return result;
    }
}

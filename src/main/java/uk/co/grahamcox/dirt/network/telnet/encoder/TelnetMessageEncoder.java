package uk.co.grahamcox.dirt.network.telnet.encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.grahamcox.dirt.network.telnet.ByteMessage;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiationMessage;
import uk.co.grahamcox.dirt.network.telnet.TelnetMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Helper to encode a Telnet Message into a set of bytes
 */
public final class TelnetMessageEncoder {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(TelnetMessageEncoder.class);

    /** The map of encoders to use */
    private final Map<Class<? extends TelnetMessage>, Function<TelnetMessage, List<Byte>>> encoders;

    /**
     * Construct the encoder
     */
    public TelnetMessageEncoder() {
        encoders = new HashMap<>();

        encoders.put(ByteMessage.class, this::encodeByteMessage);
        encoders.put(OptionNegotiationMessage.class, this::encodeNegotiationMessage);
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
            .map(bytesList -> {
                byte[] result = new byte[bytesList.size()];
                for (int i = 0; i < result.length; ++i) {
                    result[i] = bytesList.get(i);
                }
                return result;
            })
            .orElseThrow(() -> {
                LOG.error("Requested to encode unknown message class {}", message.getClass());
                return new IllegalArgumentException("No encoder found for message of type: " + message.getClass());
            });
    }

    /**
     * Generate a list of bytes that represent the given byte ID.
     * This is the single byte, if that byte is not an IAC, or the escaped IAC b if the byte is an IAC
     * @param b the byte to encode
     * @return the encoded byte
     */
    private List<Byte> encodeId(final byte b) {
        List<Byte> result = new ArrayList<>();
        if (b == TelnetBytes.IAC) {
            result.add(TelnetBytes.IAC);
        }
        result.add(b);
        return result;
    }

    /**
     * Encode the given ByteMessage to the appropriate bytes
     * @param telnetMessage the ByteMessage to encode
     * @return the bytes
     */
    private List<Byte> encodeByteMessage(final TelnetMessage telnetMessage) {
        ByteMessage byteMessage = (ByteMessage)telnetMessage;
        return encodeId(byteMessage.getValue());
    }

    /**
     * Encode the given Option Negotiation Message to the appropriate bytes
     * @param telnetMessage the OptionNegotiationMessage to encode
     * @return the bytes
     */
    private List<Byte> encodeNegotiationMessage(final TelnetMessage telnetMessage) {
        OptionNegotiationMessage negotiationMessage = (OptionNegotiationMessage)telnetMessage;

        List<Byte> result = new ArrayList<>();
        result.add(TelnetBytes.IAC);
        result.add(TelnetBytes.DO);
        result.addAll(encodeId(negotiationMessage.getOption()));

        return result;
    }
}

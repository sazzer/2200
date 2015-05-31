package uk.co.grahamcox.dirt.network.telnet.encoder;

import uk.co.grahamcox.dirt.network.telnet.TelnetMessage;

import java.util.Optional;

/**
 * Result of decoding a byte
 */
public class DecoderResult {
    /** The next state for the decoder */
    private final DecoderState nextState;

    /** The message that may have been produced from decoding a byte */
    private final Optional<TelnetMessage> message;

    /**
     * Construct the result
     * @param nextState the next state
     * @param message the message that may have been produced
     */
    public DecoderResult(final DecoderState nextState, final Optional<TelnetMessage> message) {
        this.nextState = nextState;
        this.message = message;
    }

    /**
     * Construct the result
     * @param nextState the next state
     * @param message the message that may have been produced
     */
    public DecoderResult(final DecoderState nextState, final TelnetMessage message) {
        this(nextState, Optional.of(message));
    }

    /**
     * Construct the result, where there was no message produced
     * @param nextState the next state
     */
    public DecoderResult(final DecoderState nextState) {
        this(nextState, Optional.empty());
    }

    /**
     * Get the next state
     * @return the next state
     */
    public DecoderState getNextState() {
        return nextState;
    }

    /**
     * Get the message that may have been produced
     * @return the message
     */
    public Optional<TelnetMessage> getMessage() {
        return message;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "DecoderResult{" +
            "nextState=" + nextState +
            ", message=" + message +
            '}';
    }
}

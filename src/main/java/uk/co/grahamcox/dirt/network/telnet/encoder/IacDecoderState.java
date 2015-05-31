package uk.co.grahamcox.dirt.network.telnet.encoder;

import uk.co.grahamcox.dirt.network.telnet.ByteMessage;

/**
 * Decoder State for when we've just seen a bare IAC
 */
public class IacDecoderState implements DecoderState {
    /**
     * Inject a byte into the decoder. We've just seen a bare IAC so we will either transition to a control state
     * such as Option Negotiation, or we'll transition back to NoDecoderState and emit a message
     * @param b the byte to inject
     * @return the result of injecting the byte
     */
    @Override
    public DecoderResult inject(final byte b) {
        return new DecoderResult(new NoDecoderState(), new ByteMessage(b));
    }
}

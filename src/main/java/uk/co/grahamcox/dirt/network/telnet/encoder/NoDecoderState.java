package uk.co.grahamcox.dirt.network.telnet.encoder;

import uk.co.grahamcox.dirt.network.telnet.ByteMessage;

/**
 * Implementation of the Decoder State where there is no special state
 */
public class NoDecoderState implements DecoderState {
    /**
     * Inject a single byte into the decoder. If the byte is an IAC then we transition to an IacState, otherwise we
     * transition back to a NoDecoderState
     * @param b the byte to inject
     * @return the result of decoding the byte
     */
    @Override
    public DecoderResult inject(final byte b) {
        DecoderResult result;
        if (b == TelnetBytes.IAC) {
            result = new DecoderResult(new IacDecoderState());
        } else {
            result = new DecoderResult(new NoDecoderState(), new ByteMessage(b));
        }

        return result;
    }
}

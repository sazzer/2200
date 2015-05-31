package uk.co.grahamcox.dirt.network.telnet.encoder;

import uk.co.grahamcox.dirt.network.telnet.TelnetMessage;

import java.util.Optional;

/**
 * Mechanism to decode incoming bytes into Telnet Messages
 */
public class TelnetMessageDecoder {
    /** The current state */
    private DecoderState currentState;

    /**
     * Construct the decoder, setting the current state to No State
     */
    public TelnetMessageDecoder() {
        currentState = new NoDecoderState();
    }

    /**
     * Inject a new byte into the decoder
     * @param b the byte that was injected
     * @return Telnet Message if we have just successfully decoded a message. Empty if this byte doesn't end a message
     */
    public Optional<TelnetMessage> inject(final byte b) {
        DecoderResult decoderResult = currentState.inject(b);
        currentState = decoderResult.getNextState();
        return decoderResult.getMessage();
    }
}

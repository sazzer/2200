package uk.co.grahamcox.dirt.network.telnet.encoder;

/**
 * Representation of the current state of the decoder
 */
public interface DecoderState {
    /**
     * Inject a byte into the decoder
     * @param b the byte to inject
     * @return the result of handling the byte
     */
    DecoderResult inject(byte b);
}

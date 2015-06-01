package uk.co.grahamcox.dirt.network.telnet.encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Decoder State for when we've just started an Option Subnegotiation
 */
public class StartOptionSubnegotiationDecoderState implements DecoderState {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(StartOptionSubnegotiationDecoderState.class);

    /** Flag to indicate that we've just seen an escape IAC */
    private final boolean escaped;

    /**
     * Construct the decoder state
     * @param escaped the escape flag
     */
    public StartOptionSubnegotiationDecoderState(final boolean escaped) {
        this.escaped = escaped;
    }

    /**
     * Construct the decoder state, where we've not seen an escape
     */
    public StartOptionSubnegotiationDecoderState() {
        this(false);
    }

    /**
     * Inject a byte. This is either an IAC, in which case we've got an escaped ID, or any other value, in which case
     * we've just seen the ID
     * @param b the byte to inject
     * @return the result of decoding this byte
     */
    @Override
    public DecoderResult inject(final byte b) {
        DecoderResult result;

        if (escaped) {
            if (b != TelnetBytes.IAC) {
                LOG.warn("Decoding escaped ID that wasn't an IAC: {}", b);
            }
            result = new DecoderResult(new PayloadOptionSubnegotiationDecoderState(b));
        } else if (b == TelnetBytes.IAC) {
            result = new DecoderResult(new StartOptionSubnegotiationDecoderState(true));
        } else {
            result = new DecoderResult(new PayloadOptionSubnegotiationDecoderState(b));
        }

        return result;
    }
}

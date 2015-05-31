package uk.co.grahamcox.dirt.network.telnet.encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiation;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiationMessage;

/**
 * Decoder State for when we are decoding an Option Negotiation
 */
public class OptionNegotiationDecoderState implements DecoderState {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(OptionNegotiationDecoderState.class);

    /** The negotiation that we are doing */
    private final OptionNegotiation negotiation;

    /** Indication that we are decoding an escaped ID */
    private final boolean escaped;

    /**
     * Construct the state
     * @param negotiation the negotiation that we are doing
     */
    public OptionNegotiationDecoderState(final OptionNegotiation negotiation) {
        this(negotiation, false);
    }

    /**
     * Construct the state
     * @param negotiation the negotiation that we are doing
     * @param escaped whether we've just seen an escape
     */
    public OptionNegotiationDecoderState(final OptionNegotiation negotiation, final boolean escaped) {
        this.negotiation = negotiation;
        this.escaped = escaped;
    }

    /**
     * Inject a byte into the decoder. If the byte is an IAC then we need to deal with an escaped option ID, otherwise
     * we've just seen the ID of the option that we are decoding
     * @param b the byte to inject
     * @return the result
     */
    @Override
    public DecoderResult inject(final byte b) {
        DecoderResult result;

        if (escaped) {
            if (b != TelnetBytes.IAC) {
                LOG.warn("Decoding escaped ID that wasn't an IAC: {}", b);
            }
            result = new DecoderResult(new NoDecoderState(), new OptionNegotiationMessage(negotiation, b));
        } else if (b == TelnetBytes.IAC) {
            result = new DecoderResult(new OptionNegotiationDecoderState(negotiation, true));
        } else {
            result = new DecoderResult(new NoDecoderState(), new OptionNegotiationMessage(negotiation, b));
        }
        return result;
    }
}

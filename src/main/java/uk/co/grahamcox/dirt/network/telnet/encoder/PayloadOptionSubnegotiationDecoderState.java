package uk.co.grahamcox.dirt.network.telnet.encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.grahamcox.dirt.network.telnet.OptionSubNegotiationMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Decoder State for when we are decoding the payload of an Option Subnegotiation
 */
public class PayloadOptionSubnegotiationDecoderState implements DecoderState {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(StartOptionSubnegotiationDecoderState.class);

    /** The ID of the Option */
    private final byte id;

    /** The payload we've decoded so far */
    private final List<Byte> payload;

    /** Flag to indicate that we've just seen an escape IAC */
    private final boolean escaped;

    /**
     * Construct the state
     * @param id the ID of the option we're doing subnegotiation for
     * @param payload the payload that we've received so far
     * @param escaped the escape flag
     */
    public PayloadOptionSubnegotiationDecoderState(final byte id,
                                                   final List<Byte> payload,
                                                   final boolean escaped) {
        this.id = id;
        this.payload = payload;
        this.escaped = escaped;
    }

    /**
     * Construct the state
     * @param id the ID of the option we're doing subnegotiation for
     * @param payload the payload that we've received so far
     */
    public PayloadOptionSubnegotiationDecoderState(final byte id,
                                                   final List<Byte> payload) {
        this(id, payload, false);
    }

    /**
     * Construct the state, with no payload at all
     * @param id the ID of the option we're doing subnegotiation for
     */
    public PayloadOptionSubnegotiationDecoderState(final byte id) {
        this(id, Collections.emptyList(), false);
    }

    /**
     * Inject the byte into the decoder. If this is an IAC then either we're escaping an IAC or we're about to get an SE
     * Otherwise it's just a byte in the payload
     * @param b the byte to inject
     * @return the result of injecting the byte
     */
    @Override
    public DecoderResult inject(final byte b) {
        DecoderResult result;

        if (escaped) {
            if (b == TelnetBytes.SE) {
                byte[] resultPayload = new byte[payload.size()];
                for (int i = 0; i < resultPayload.length; ++i) {
                    resultPayload[i] = payload.get(i);
                }

                result = new DecoderResult(new NoDecoderState(),
                    new OptionSubNegotiationMessage(id, resultPayload));
            } else if (b == TelnetBytes.IAC) {
                List<Byte> newPayload = new ArrayList<>(payload);
                newPayload.add(b);
                result = new DecoderResult(new PayloadOptionSubnegotiationDecoderState(id, newPayload, false));
            } else {
                LOG.warn("Decoding escaped payload that wasn' an IAC: {}", b);

                List<Byte> newPayload = new ArrayList<>(payload);
                newPayload.add(b);
                result = new DecoderResult(new PayloadOptionSubnegotiationDecoderState(id, newPayload, false));
            }
        } else if (b == TelnetBytes.IAC) {
            result = new DecoderResult(new PayloadOptionSubnegotiationDecoderState(id, payload, true));
        } else {
            List<Byte> newPayload = new ArrayList<>(payload);
            newPayload.add(b);
            result = new DecoderResult(new PayloadOptionSubnegotiationDecoderState(id, newPayload, false));
        }

        return result;
    }
}

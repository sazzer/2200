package uk.co.grahamcox.dirt.network.telnet.encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.grahamcox.dirt.network.telnet.AbortOutputMessage;
import uk.co.grahamcox.dirt.network.telnet.AreYouThereMessage;
import uk.co.grahamcox.dirt.network.telnet.BreakMessage;
import uk.co.grahamcox.dirt.network.telnet.ByteMessage;
import uk.co.grahamcox.dirt.network.telnet.DataMarkMessage;
import uk.co.grahamcox.dirt.network.telnet.EraseCharacterMessage;
import uk.co.grahamcox.dirt.network.telnet.EraseLineMessage;
import uk.co.grahamcox.dirt.network.telnet.GoAheadMessage;
import uk.co.grahamcox.dirt.network.telnet.InterruptMessage;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Decoder State for when we've just seen a bare IAC
 */
public class IacDecoderState implements DecoderState {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(IacDecoderState.class);

    /** The map of methods to use for each possible input byte */
    private final Map<Byte, Supplier<DecoderResult>> injectMap;

    /**
     * Construct the decoder state, setting up the handlers for each input byte
     */
    public IacDecoderState() {
        injectMap = new HashMap<>();
        injectMap.put(TelnetBytes.IAC, () ->
            new DecoderResult(new NoDecoderState(), new ByteMessage(TelnetBytes.IAC)));
        injectMap.put(TelnetBytes.DO, () ->
            new DecoderResult(new OptionNegotiationDecoderState(OptionNegotiation.DO)));
        injectMap.put(TelnetBytes.DONT, () ->
            new DecoderResult(new OptionNegotiationDecoderState(OptionNegotiation.DONT)));
        injectMap.put(TelnetBytes.WILL, () ->
            new DecoderResult(new OptionNegotiationDecoderState(OptionNegotiation.WILL)));
        injectMap.put(TelnetBytes.WONT, () ->
            new DecoderResult(new OptionNegotiationDecoderState(OptionNegotiation.WONT)));
        injectMap.put(TelnetBytes.SB, () ->
            new DecoderResult(new StartOptionSubnegotiationDecoderState()));
        injectMap.put(TelnetBytes.DATA_MARK, () ->
            new DecoderResult(new NoDecoderState(), new DataMarkMessage()));
        injectMap.put(TelnetBytes.BREAK, () ->
            new DecoderResult(new NoDecoderState(), new BreakMessage()));
        injectMap.put(TelnetBytes.INTERRUPT, () ->
            new DecoderResult(new NoDecoderState(), new InterruptMessage()));
        injectMap.put(TelnetBytes.ABORT_OUTPUT, () ->
            new DecoderResult(new NoDecoderState(), new AbortOutputMessage()));
        injectMap.put(TelnetBytes.ARE_YOU_THERE, () ->
            new DecoderResult(new NoDecoderState(), new AreYouThereMessage()));
        injectMap.put(TelnetBytes.ERASE_CHARACTER, () ->
            new DecoderResult(new NoDecoderState(), new EraseCharacterMessage()));
        injectMap.put(TelnetBytes.ERASE_LINE, () ->
            new DecoderResult(new NoDecoderState(), new EraseLineMessage()));
        injectMap.put(TelnetBytes.GO_AHEAD, () ->
            new DecoderResult(new NoDecoderState(), new GoAheadMessage()));
    }

    /**
     * Inject a byte into the decoder. We've just seen a bare IAC so we will either transition to a control state
     * such as Option Negotiation, or we'll transition back to NoDecoderState and emit a message
     * @param b the byte to inject
     * @return the result of injecting the byte
     */
    @Override
    public DecoderResult inject(final byte b) {
        return Optional.ofNullable(injectMap.get(b))
            .map(Supplier::get)
            .orElseGet(() -> {
                LOG.warn("Unexpected byte {} following IAC", b);
                return new DecoderResult(new NoDecoderState(), new ByteMessage(b));
            });
    }
}

package uk.co.grahamcox.dirt.network.telnet.options;

/**
 * Details of the Suppress Go Ahead Option
 */
@Option(id = SuppressGoAheadOption.OPTION_ID, target = OptionTarget.CLIENT)
public class SuppressGoAheadOption implements TelnetOption {
    /** The ID of the Option */
    public static final byte OPTION_ID = 3;

    /**
     * Handle a SubNegotiation being received
     * @param payload the payload of the subnegotiation
     */
    @Override
    public void handleSubNegotiation(final byte[] payload) {

    }
}

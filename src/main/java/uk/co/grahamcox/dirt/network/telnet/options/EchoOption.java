package uk.co.grahamcox.dirt.network.telnet.options;

/**
 * Details of the Echo Option
 */
@Option(id = EchoOption.OPTION_ID, target = OptionTarget.SERVER)
public class EchoOption implements TelnetOption {
    /** The ID of the Option */
    public static final byte OPTION_ID = 1;

    /**
     * Handle a SubNegotiation being received
     * @param payload the payload of the subnegotiation
     */
    @Override
    public void handleSubNegotiation(final byte[] payload) {

    }
}

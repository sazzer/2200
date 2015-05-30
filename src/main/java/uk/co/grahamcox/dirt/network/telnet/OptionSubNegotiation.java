package uk.co.grahamcox.dirt.network.telnet;

import java.util.Arrays;

/**
 * Telnet Message representing an option subnegotiation
 */
public class OptionSubNegotiation implements TelnetMessage {
    /** The option ID */
    private final byte option;

    /** The subnegotiation data */
    private final byte[] subnegotiation;

    /**
     * Construct the message
     * @param option the option ID
     * @param subnegotiation the subnegotiation data
     */
    public OptionSubNegotiation(byte option, byte[] subnegotiation) {
        this.option = option;
        this.subnegotiation = subnegotiation;
    }

    /**
     * Get the Option ID
     * @return the Option ID
     */
    public byte getOption() {
        return option;
    }

    /**
     * Get the subnegotiation data
     * @return the subnegotiation data
     */
    public byte[] getSubnegotiation() {
        return subnegotiation;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "OptionSubNegotiation{" +
            "option=" + option +
            ", subnegotiation=" + Arrays.toString(subnegotiation) +
            '}';
    }
}

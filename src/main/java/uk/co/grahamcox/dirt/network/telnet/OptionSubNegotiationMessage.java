package uk.co.grahamcox.dirt.network.telnet;

import java.util.Arrays;
import java.util.Objects;

/**
 * Telnet Message representing an option subnegotiation
 */
public class OptionSubNegotiationMessage implements TelnetMessage {
    /** The option ID */
    private final byte option;

    /** The subnegotiation data */
    private final byte[] subnegotiation;

    /**
     * Construct the message
     * @param option the option ID
     * @param subnegotiation the subnegotiation data
     */
    public OptionSubNegotiationMessage(final byte option, final byte[] subnegotiation) {
        this.option = option;
        this.subnegotiation = new byte[subnegotiation.length];
        System.arraycopy(subnegotiation, 0, this.subnegotiation, 0, subnegotiation.length);
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
        byte[] result = new byte[this.subnegotiation.length];
        System.arraycopy(subnegotiation, 0, result, 0, subnegotiation.length);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OptionSubNegotiationMessage that = (OptionSubNegotiationMessage) o;
        return Objects.equals(option, that.option) &&
            Arrays.equals(subnegotiation, that.subnegotiation);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(option, subnegotiation);
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

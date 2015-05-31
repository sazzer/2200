package uk.co.grahamcox.dirt.network.telnet;

import java.util.Objects;

/**
 * Telnet Message representing an option negotiation
 */
public class OptionNegotiationMessage implements TelnetMessage {
    /** the negotiation */
    private final OptionNegotiation negotiation;

    /** The option to be negotiatied */
    private final byte option;

    /**
     * Construct the message
     * @param negotiation the negotiation
     * @param option the option
     */
    public OptionNegotiationMessage(final OptionNegotiation negotiation, final byte option) {
        this.negotiation = negotiation;
        this.option = option;
    }

    /**
     * Get the negotiation
     * @return the negotiation
     */
    public OptionNegotiation getNegotiation() {
        return negotiation;
    }

    /**
     * Get the option
     * @return the option
     */
    public byte getOption() {
        return option;
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
        OptionNegotiationMessage that = (OptionNegotiationMessage) o;
        return Objects.equals(option, that.option) &&
            Objects.equals(negotiation, that.negotiation);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(negotiation, option);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "OptionNegotiationMessage{" +
            "negotiation=" + negotiation +
            ", option=" + option +
            '}';
    }
}

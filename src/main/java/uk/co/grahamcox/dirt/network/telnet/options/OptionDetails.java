package uk.co.grahamcox.dirt.network.telnet.options;

import uk.co.grahamcox.dirt.network.telnet.OptionNegotiation;

import java.util.Optional;

/**
 * Representation of the details of an option
 */
public class OptionDetails {
    /** The actual option */
    private final TelnetOption option;

    /** The ID of the option */
    private final byte id;

    /** The target of the option */
    private final OptionTarget target;

    /** The negotiation that we sent for this option */
    private Optional<OptionNegotiation> sentNegotiation;

    /** The negotiation that we received for this option */
    private Optional<OptionNegotiation> receivedNegotiation;

    /**
     * Construct the option details
     * @param option the option
     */
    public OptionDetails(final TelnetOption option) {
        this.option = option;

        Option optionAnnotation = option.getClass().getAnnotation(Option.class);
        if (optionAnnotation == null) {
            throw new IllegalArgumentException("Provided Option is lacking the @Option annotation");
        }

        id = optionAnnotation.id();
        target = optionAnnotation.target();
        sentNegotiation = Optional.empty();
        receivedNegotiation = Optional.empty();
    }

    /**
     * Get the raw option
     * @return the raw option
     */
    public TelnetOption getOption() {
        return option;
    }

    /**
     * Get the ID of the option
     * @return the ID of the option
     */
    public byte getId() {
        return id;
    }

    /**
     * Get the target of the option
     * @return the target of the option
     */
    public OptionTarget getTarget() {
        return target;
    }

    /**
     * Record the last negotiation that we sent for this option
     * @param negotiation the negotiation that we sent
     */
    public void sentNegotiation(final OptionNegotiation negotiation) {
        boolean activeBefore = isActive();
        this.sentNegotiation = Optional.of(negotiation);
        boolean activeAfter = isActive();

        if (!activeBefore && activeAfter) {
            System.out.println("Activated");
        } else if (activeBefore && !activeAfter) {
            System.out.println("Deactivated");
        }
    }

    /**
     * Record the last negotiation that we received for this option
     * @param negotiation the negotiation that we received
     */
    public void receivedNegotiation(final OptionNegotiation negotiation) {
        boolean activeBefore = isActive();
        this.receivedNegotiation = Optional.of(negotiation);
        boolean activeAfter = isActive();

        if (!activeBefore && activeAfter) {
            System.out.println("Activated");
        } else if (activeBefore && !activeAfter) {
            System.out.println("Deactivated");
        }
    }

    /**
     * Check if the option is active
     * @return true if the option is active. False if not
     */
    public boolean isActive() {
        boolean active = false;
        if (sentNegotiation.isPresent() && receivedNegotiation.isPresent()) {
            OptionNegotiation sent = sentNegotiation.get();
            OptionNegotiation received = receivedNegotiation.get();
            active = (sent == OptionNegotiation.WILL && received == OptionNegotiation.DO) ||
                (sent == OptionNegotiation.DO && received == OptionNegotiation.WILL);
        }
        return active;
    }
}

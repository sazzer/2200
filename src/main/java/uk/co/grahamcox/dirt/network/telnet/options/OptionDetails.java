package uk.co.grahamcox.dirt.network.telnet.options;

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
}

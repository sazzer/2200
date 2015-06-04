package uk.co.grahamcox.dirt.network.telnet.options;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Manager of all the options for a specific connection
 */
public class OptionManager {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(OptionManager.class);

    /** Cache of the options by their ID */
    private final transient Map<Byte, TelnetOption> optionsById;

    /**
     * Construct the option manager
     * @param options the options
     */
    public OptionManager(final Set<TelnetOption> options) {
        optionsById = new HashMap<>();

        options.stream().filter(o -> {
            OptionId optionId = o.getClass().getAnnotation(OptionId.class);
            return optionId != null;
        }).forEach(option -> {
            OptionId optionId = option.getClass().getAnnotation(OptionId.class);
            optionsById.put(optionId.value(), option);
        });
    }

    /**
     * Handle negotiation of an option
     * @param id the ID of the Option
     * @param negotiation the negotiation
     */
    public void handleNegotiation(final byte id, final OptionNegotiation negotiation) {
        Optional<TelnetOption> option = findOption(id);
        if (option.isPresent()) {
            LOG.debug("Negotiation of option {}: {}", option, negotiation);
        } else {
            LOG.warn("Negotiation of unknown option {}: {}", id, negotiation);
        }
    }

    /**
     * Handle Subnegotiation of an option
     * @param id the ID of the Option
     * @param payload the payload of the subnegotiation
     */
    public void handleSubnegotiation(final byte id, final byte[] payload) {
        Optional<TelnetOption> option = findOption(id);
        if (option.isPresent()) {
            LOG.debug("Subnegotiation of option {}: {}", option, Arrays.toString(payload));
        } else {
            LOG.warn("Subnegotiation of unknown option {}", id);
        }
    }

    /**
     * Get a stream of all the options that we support
     * @return the set of all the options we support
     */
    public Stream<TelnetOption> getAllOptions() {
        return optionsById.values().stream();
    }

    /**
     * Find the option with the given ID
     * @param id the ID
     * @return the option
     */
    private Optional<TelnetOption> findOption(final byte id) {
        return Optional.ofNullable(optionsById.get(id));
    }
}

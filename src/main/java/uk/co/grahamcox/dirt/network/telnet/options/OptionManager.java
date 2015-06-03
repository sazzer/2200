package uk.co.grahamcox.dirt.network.telnet.options;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiation;

import java.util.Arrays;

/**
 * Manager of all the options for a specific connection
 */
public class OptionManager {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(OptionManager.class);

    /**
     * Handle negotiation of an option
     * @param id the ID of the Option
     * @param negotiation the negotiation
     */
    public void handleNegotiation(byte id, OptionNegotiation negotiation) {
        LOG.debug("Negotiation of option {}: {}", id, negotiation);
    }

    /**
     * Handle Subnegotiation of an option
     * @param id the ID of the Option
     * @param payload the payload of the subnegotiation
     */
    public void handleSubnegotiation(byte id, byte[] payload) {
        LOG.debug("Subnegotiation of option {}: {}", id, Arrays.toString(payload));
    }
}

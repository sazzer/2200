package uk.co.grahamcox.dirt.network.telnet.options;

/**
 * Interface describing a telnet option that we can support
 */
public interface TelnetOption {
    /**
     * Handle a SubNegotiation being received
     * @param payload the payload of the subnegotiation
     */
    void handleSubNegotiation(byte[] payload);
}

package uk.co.grahamcox.dirt.network.telnet;

/**
 * Telnet Message representing a normal byte
 */
public class ByteMessage implements TelnetMessage {
    /** The actual byte value of the message */
    private final byte value;

    /**
     * Construct the byte message
     * @param value the value of the byte
     */
    public ByteMessage(byte value) {
        this.value = value;
    }

    /**
     * Get the value of the message
     * @return the byte value of the message
     */
    public byte getValue() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "ByteMessage{" +
            "value=" + value +
            '}';
    }
}

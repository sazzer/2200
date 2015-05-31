package uk.co.grahamcox.dirt.network.telnet;

import java.util.Objects;

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
    public ByteMessage(final byte value) {
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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ByteMessage that = (ByteMessage) o;
        return Objects.equals(value, that.value);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "ByteMessage{" +
            "value=" + value +
            '}';
    }
}

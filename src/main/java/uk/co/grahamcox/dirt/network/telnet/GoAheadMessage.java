package uk.co.grahamcox.dirt.network.telnet;

import java.util.Objects;

/**
 * Telnet Message representing a Go Ahead message
 */
public class GoAheadMessage implements TelnetMessage {
    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(this.getClass());
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "GoAheadMessage{}";
    }
}

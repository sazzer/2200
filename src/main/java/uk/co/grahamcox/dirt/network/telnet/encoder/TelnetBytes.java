package uk.co.grahamcox.dirt.network.telnet.encoder;

/**
 * Collection of the special Telnet Bytes
 */
public final class TelnetBytes {
    /** The IAC byte */
    public static final byte IAC = (byte)0xff;
    /** The DONT byte */
    public static final byte DONT = (byte)0xfe;
    /** The DO byte */
    public static final byte DO = (byte)0xfd;
    /** The WONT byte */
    public static final byte WONT = (byte)0xfc;
    /** The WILL byte */
    public static final byte WILL = (byte)0xfb;
    /** The SB byte */
    public static final byte SB = (byte)0xfa;
    /** The SE byte */
    public static final byte SE = (byte)0xf0;

    /**
     * Private Constructor
     */
    private TelnetBytes() {

    }
}

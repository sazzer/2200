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
    /** The Go Ahead byte */
    public static final byte GO_AHEAD = (byte)0xf9;
    /** The Erase Line byte */
    public static final byte ERASE_LINE = (byte)0xf8;
    /** The Erase Character byte */
    public static final byte ERASE_CHARACTER = (byte)0xf7;
    /** The Are You There byte */
    public static final byte ARE_YOU_THERE = (byte)0xf6;
    /** The Abort Output byte */
    public static final byte ABORT_OUTPUT = (byte)0xf5;
    /** The Interrupt byte */
    public static final byte INTERRUPT = (byte)0xf4;
    /** The Break byte */
    public static final byte BREAK = (byte)0xf3;
    /** The Data Mark byte */
    public static final byte DATA_MARK = (byte)0xf2;
    /** The NOP byte */
    public static final byte NOP = (byte)0xf1;
    /** The SE byte */
    public static final byte SE = (byte)0xf0;

    /**
     * Private Constructor
     */
    private TelnetBytes() {

    }
}

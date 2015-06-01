package uk.co.grahamcox.dirt.network.telnet.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import uk.co.grahamcox.dirt.network.telnet.encoder.TelnetMessageDecoder;

import java.util.List;

/**
 * Netty ByteToMessageDecoder to use to decode a TelnetMessage from bytes
 */
public class TelnetNettyDecoder extends ByteToMessageDecoder {
    /** The decoder to use */
    private final TelnetMessageDecoder decoder;

    /**
     * Construct the netty decoder
     */
    public TelnetNettyDecoder() {
        decoder = new TelnetMessageDecoder();
    }

    /**
     * Decode the newly received bytes into Telnet Messages to be passed on down the chain
     * @param ctx the context
     * @param in the input bytes
     * @param out the output list of messages
     */
    @Override
    protected void decode(final ChannelHandlerContext ctx,
                          final ByteBuf in,
                          final List<Object> out) {
        while (in.isReadable()) {
            decoder.inject(in.readByte())
                .ifPresent(out::add);
        }
    }
}

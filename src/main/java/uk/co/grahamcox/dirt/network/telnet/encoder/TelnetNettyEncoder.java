package uk.co.grahamcox.dirt.network.telnet.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.grahamcox.dirt.network.telnet.TelnetMessage;

/**
 * Netty MessageToByteEncoder to use to encode a TelnetMessage into bytes
 */
public class TelnetNettyEncoder extends MessageToByteEncoder<TelnetMessage> {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(TelnetNettyEncoder.class);

    /** The message encoder to use to do the real work */
    private final TelnetMessageEncoder messageEncoder = new TelnetMessageEncoder();

    /**
     * Encode the given message onto the given output
     * @param ctx the context
     * @param msg the message to encode
     * @param out the output to write to
     * @throws Exception if the message fails to encode
     */
    @Override
    protected void encode(final ChannelHandlerContext ctx,
                          final TelnetMessage msg,
                          final ByteBuf out)
        throws Exception {

        LOG.debug("Sending message {} to client {}", msg, ctx.channel());
        byte[] encoded = messageEncoder.encode(msg);
        out.writeBytes(encoded);
    }
}

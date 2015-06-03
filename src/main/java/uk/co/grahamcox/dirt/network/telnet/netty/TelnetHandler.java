package uk.co.grahamcox.dirt.network.telnet.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiationMessage;
import uk.co.grahamcox.dirt.network.telnet.OptionSubNegotiationMessage;
import uk.co.grahamcox.dirt.network.telnet.options.OptionManager;

/**
 * Handler that handles all of the telnet nonsense and makes sense of it
 */
public class TelnetHandler extends ChannelInboundHandlerAdapter {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(TelnetHandler.class);

    /** The option manager for this connection */
    private final OptionManager optionManager;

    /**
     * Construct the handler
     * @param optionManager the option manager to use
     */
    public TelnetHandler(OptionManager optionManager) {
        this.optionManager = optionManager;
    }

    /**
     * Callback for when the handler becomes active - i.e. when the client has connected
     * @param ctx the context
     * @throws Exception hopefully never
     */
    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        LOG.debug("Received new connection from {}", ctx.channel());
    }

    /**
     * Callback for when the handler becomes inactive - i.e. when the client has disconnected
     * @param ctx the context
     * @throws Exception hopefully never
     */
    @Override
    public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        LOG.debug("Connection closed to client {}", ctx.channel());
    }

    /**
     * Callback for when data is available to read
     * @param ctx the context
     * @param msg the message that was read
     * @throws Exception hopefully never
     */
    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        LOG.debug("Received message: {}", msg);
        if (msg instanceof OptionNegotiationMessage) {
            OptionNegotiationMessage optionNegotiation = (OptionNegotiationMessage)msg;
            optionManager.handleNegotiation(optionNegotiation.getOption(), optionNegotiation.getNegotiation());
        } else if (msg instanceof OptionSubNegotiationMessage) {
            OptionSubNegotiationMessage optionSubNegotiationMessage = (OptionSubNegotiationMessage)msg;
            optionManager.handleSubnegotiation(optionSubNegotiationMessage.getOption(),
                optionSubNegotiationMessage.getSubnegotiation());
        }
        super.channelRead(ctx, "Hello, World");
    }
}

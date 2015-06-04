package uk.co.grahamcox.dirt.network.telnet.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiationMessage;
import uk.co.grahamcox.dirt.network.telnet.options.OptionManager;

/**
 * Channel Handler for handling Option Negotiation messages
 */
public class TelnetOptionNegotiationHandler extends SimpleChannelInboundHandler<OptionNegotiationMessage> {
    /** The option manager for this connection */
    private final OptionManager optionManager;
    /**
     * Construct the handler, ensuring that messages are not automatically released
     * @param optionManager the option manager to use
     */
    public TelnetOptionNegotiationHandler(final OptionManager optionManager) {
        super(OptionNegotiationMessage.class, false);
        this.optionManager = optionManager;
    }

    /**
     * Handle the given Option Negotiation message
     * @param ctx the context
     * @param msg the message
     */
    @Override
    protected void channelRead0(final ChannelHandlerContext ctx,
                                final OptionNegotiationMessage msg) throws Exception {
        optionManager.handleNegotiation(msg.getOption(), msg.getNegotiation());
    }
}

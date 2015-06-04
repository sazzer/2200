package uk.co.grahamcox.dirt.network.telnet.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import uk.co.grahamcox.dirt.network.telnet.OptionSubNegotiationMessage;
import uk.co.grahamcox.dirt.network.telnet.options.OptionManager;

/**
 * Channel Handler for handling Option Subnegotiation messages
 */
public class TelnetOptionSubnegotiationHandler extends SimpleChannelInboundHandler<OptionSubNegotiationMessage> {
    /** The option manager for this connection */
    private final OptionManager optionManager;
    /**
     * Construct the handler, ensuring that messages are not automatically released
     * @param optionManager the option manager to use
     */
    public TelnetOptionSubnegotiationHandler(final OptionManager optionManager) {
        super(OptionSubNegotiationMessage.class, false);
        this.optionManager = optionManager;
    }

    /**
     * Handle the given Option Subnegotiation message
     * @param ctx the context
     * @param msg the message
     */
    @Override
    protected void channelRead0(final ChannelHandlerContext ctx,
                                final OptionSubNegotiationMessage msg) throws Exception {
        optionManager.handleSubnegotiation(msg.getOption(), msg.getSubnegotiation());
    }
}

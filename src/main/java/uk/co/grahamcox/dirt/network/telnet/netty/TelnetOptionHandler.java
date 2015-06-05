package uk.co.grahamcox.dirt.network.telnet.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiation;
import uk.co.grahamcox.dirt.network.telnet.OptionNegotiationMessage;
import uk.co.grahamcox.dirt.network.telnet.OptionSubNegotiationMessage;
import uk.co.grahamcox.dirt.network.telnet.options.OptionManager;
import uk.co.grahamcox.dirt.network.telnet.options.OptionTarget;

/**
 * Channel Handler to handle Telnet Options
 */
public class TelnetOptionHandler extends ChannelInboundHandlerAdapter {
    /** The option manager */
    private final OptionManager optionManager;

    /**
     * Construct the handler
     * @param optionManager the option manager
     */
    public TelnetOptionHandler(final OptionManager optionManager) {
        this.optionManager = optionManager;
    }

    /**
     * Handle a new client connecting, sending the request for all of the options
     * @param ctx the context
     * @throws Exception if an error occurs
     */
    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        optionManager.getAllOptions().forEach(o -> {
            OptionNegotiation negotiation;
            if (o.getTarget() == OptionTarget.CLIENT) {
                negotiation = OptionNegotiation.DO;
            } else {
                negotiation = OptionNegotiation.WILL;
            }

            ctx.channel().write(new OptionNegotiationMessage(negotiation, o.getId()));
        });

        ctx.channel().flush();
        super.channelActive(ctx);
    }

    /**
     * Handle a message being received from the client
     * @param ctx the context
     * @param msg the message
     * @throws Exception if an error occurs
     */
    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        if (msg instanceof OptionNegotiationMessage) {
            handleOptionNegotiation((OptionNegotiationMessage)msg);
        } else if (msg instanceof OptionSubNegotiationMessage) {
            handleOptionSubnegotiation((OptionSubNegotiationMessage)msg);
        } else {
            super.channelRead(ctx, msg);
        }
    }

    /**
     * Handle an Option Negotiation message
     * @param optionNegotiationMessage the message
     */
    private void handleOptionNegotiation(final OptionNegotiationMessage optionNegotiationMessage) {
        optionManager.handleNegotiation(optionNegotiationMessage.getOption(),
            optionNegotiationMessage.getNegotiation());
    }

    /**
     * Handle an Option Subnegotiation message
     * @param optionSubNegotiationMessage the message
     */
    private void handleOptionSubnegotiation(final OptionSubNegotiationMessage optionSubNegotiationMessage) {
        optionManager.handleSubnegotiation(optionSubNegotiationMessage.getOption(),
            optionSubNegotiationMessage.getSubnegotiation());
    }
}

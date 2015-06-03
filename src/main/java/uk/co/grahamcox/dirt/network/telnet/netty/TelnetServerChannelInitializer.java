package uk.co.grahamcox.dirt.network.telnet.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.logging.LoggingHandler;
import uk.co.grahamcox.dirt.network.telnet.options.OptionManager;

/**
 * Channel Initializer for the Telnet Server
 */
class TelnetServerChannelInitializer extends ChannelInitializer<Channel> {
    /**
     * Initialize the provided channel
     * @param channel the channel to initialize
     */
    @Override
    protected void initChannel(final Channel channel) {
        OptionManager optionManager = new OptionManager();

        channel.pipeline().addLast(new TelnetNettyEncoder());
        channel.pipeline().addLast(new TelnetNettyDecoder());
        channel.pipeline().addLast(new LoggingHandler());
        channel.pipeline().addLast(new TelnetHandler(optionManager));
    }
}

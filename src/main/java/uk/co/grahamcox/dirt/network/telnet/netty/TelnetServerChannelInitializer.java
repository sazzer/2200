package uk.co.grahamcox.dirt.network.telnet.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

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
        channel.pipeline().addLast(new TelnetNettyEncoder());
        channel.pipeline().addLast(new TelnetNettyDecoder());
        channel.pipeline().addLast(new TelnetHandler());
    }
}

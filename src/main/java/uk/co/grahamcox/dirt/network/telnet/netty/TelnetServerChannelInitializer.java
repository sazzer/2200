package uk.co.grahamcox.dirt.network.telnet.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.logging.LoggingHandler;
import uk.co.grahamcox.dirt.network.telnet.options.EchoOption;
import uk.co.grahamcox.dirt.network.telnet.options.OptionManager;
import uk.co.grahamcox.dirt.network.telnet.options.TelnetOption;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Set<TelnetOption> options = Stream.of(new EchoOption())
            .collect(Collectors.toSet());

        OptionManager optionManager = new OptionManager(options);

        channel.pipeline().addLast(new TelnetNettyEncoder());
        channel.pipeline().addLast(new TelnetNettyDecoder());
        channel.pipeline().addLast(new LoggingHandler());
        channel.pipeline().addLast(new TelnetHandler(optionManager));
    }
}

package uk.co.grahamcox.dirt.network.telnet.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wrapper around the actual telnet server
 */
public class TelnetServer {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(TelnetServer.class);

    /** The number of connectons in the accept backlog */
    private static final int DEFAULT_ACCEPT_BACKLOG = 128;

    /** The port to listen on */
    private final short port;

    /**
     * Construct the server
     * @param port the port to listen on
     */
    public TelnetServer(final short port) {
        this.port = port;
    }

    /**
     * Start running the server
     */
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new TelnetServerChannelInitializer())
                .option(ChannelOption.SO_BACKLOG, DEFAULT_ACCEPT_BACKLOG)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOG.error("The server was interrupted unexpectedly", e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}

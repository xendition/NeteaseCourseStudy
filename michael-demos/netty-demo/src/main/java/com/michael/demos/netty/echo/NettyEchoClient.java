package com.michael.demos.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 类功能描述:
 * <pre>
 *   简单使用Netty - 客户端
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/25 12:31
 */
public class NettyEchoClient {

    private static final String DEFAULT_HOST = "10000";
    private static final String DEFAULT_PORT = "10000";
    private static final String DEFAULT_SIZE = "512";

    static final String SERVER_HOST = System.getProperty("host", DEFAULT_HOST);
    static final int SERVER_PORT = Integer.parseInt(System.getProperty("port", DEFAULT_PORT));
    static final int SIZE = Integer.parseInt(System.getProperty("size", DEFAULT_SIZE));

    public static void main(String[] args) throws Exception {
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();

            // 配置并启动客户端
            ChannelFuture channelFuture = bootstrap
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new EchoClientHandler());
                        }
                    })
                    .connect(SERVER_HOST, SERVER_PORT).sync();

            // Wait until the connection is closed.
            channelFuture.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}

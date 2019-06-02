package com.michael.demos.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 类功能描述:
 * <pre>
 *  简单使用Netty - 服务端 - 直接返回用户请求的信息
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/25 11:34
 */
public class NettyEchoServer {

    private static final String DEFAULT_PORT = "10000";

    static final int SERVER_PORT = Integer.parseInt(System.getProperty("port", DEFAULT_PORT));

    public static void main(String[] args) throws Exception {

        // 创建 EventLoopGroup   accept线程组 NioEventLoop
        EventLoopGroup mainGroup = new NioEventLoopGroup(1);

        // 创建 EventLoopGroup   I/O线程组
        EventLoopGroup workGroup = new NioEventLoopGroup(2);

        try {
            // 服务端启动引导工具类
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            // 服务端处理器 - 由于是多请求共享的，所以需要在 EchoServerHandler 上增加share注解
            final EchoServerHandler serverHandler = new EchoServerHandler();

            // 配置服务端处理的reactor线程组以及服务端的其他配置
            serverBootstrap
                    .group(mainGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(serverHandler);
                        }
                    });

            // 通过bind启动服务
            ChannelFuture f = serverBootstrap.bind(SERVER_PORT).sync();


            // 阻塞主线程，直到网络服务被关闭
            f.channel().closeFuture().sync();
        } finally {
            // 关闭所有事件循环以终止所有线程。
            // 关闭线程组
            mainGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}

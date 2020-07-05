package com;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(eventExecutors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        SSLEngine engine = ContextSSLFactory.getSslContext2().createSSLEngine();
                        engine.setUseClientMode(true);

                        //pipeline.addLast("ssl", new SslHandler(engine));
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                System.out.println(s);
                            }
                        });
                    }
                });
        SocketAddress address = new InetSocketAddress("127.0.0.1", 2333);
        final ChannelFuture future = bootstrap.connect(address).sync();
//        channel = future.awaitUninterruptibly().channel();
//        System.out.println("连接成功， channel =" + channel.remoteAddress());
        future.channel().writeAndFlush("aaa");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            scanner.next();
            boolean active = future.channel().isActive();
            System.out.println(active);//握手成功后 会自动关闭 这个channel
            future.channel().writeAndFlush("aaa");
        }
    }
}

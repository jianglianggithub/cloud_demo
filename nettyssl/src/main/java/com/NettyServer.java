package com;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.net.ssl.SSLEngine;
import java.net.InetAddress;
import java.nio.ByteBuffer;

@Component
public class NettyServer {


    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup b = new NioEventLoopGroup(1);
        NioEventLoopGroup w = new NioEventLoopGroup(1);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(b, w)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        SSLEngine engine = ContextSSLFactory.getSslContext().createSSLEngine();
                        engine.setUseClientMode(false); //设置为服务端模式
                        engine.setNeedClientAuth(true); //需要验证客户端

                        pipeline.addLast("ssl", new SslHandler(engine));
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new SimpleChannelInboundHandler<String>() {
                                             @Override
                                             protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                                 System.out.println("客户端发来的消息 " + s);
                                             }

                                             @Override
                                             public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                                 System.out.println("activie server");
                                                 ctx.pipeline().get(SslHandler.class).handshakeFuture().addListener(
                                                         new GenericFutureListener<Future<Channel>>() {
                                                             @Override
                                                             public void operationComplete(Future<Channel> future) throws Exception {
                                                                 if (future.isSuccess()) {
                                                                     System.out.println("----握手成功");

                                                                     ctx.channel().writeAndFlush("握手成功");
                                                                 } else {
                                                                     System.out.println("----握手失败");
                                                                     Thread.sleep(3000);
                                                                     System.out.println(ctx.channel().isActive());
                                                                     ctx.channel().writeAndFlush("握手失败");

                                                                 }
                                                             }
                                                         });
                                                 super.channelActive(ctx);
                                             }
                                         }


                        );
                    }
                });


        ChannelFuture channel = serverBootstrap.bind(2333).sync();
        channel.channel().closeFuture().sync();

    }

}

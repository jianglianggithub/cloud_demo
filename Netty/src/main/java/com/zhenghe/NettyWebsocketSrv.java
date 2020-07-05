package com.zhenghe;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.Charset;

@Component
public class NettyWebsocketSrv {


    private final ServerBootstrap srv;
    private  ChannelFuture channelFuture;
    private final NioEventLoopGroup bos;
    private final NioEventLoopGroup worker;
    public NettyWebsocketSrv(){
        bos = new NioEventLoopGroup(1);
        worker = new NioEventLoopGroup();
        srv=new ServerBootstrap();

        srv.channel(NioServerSocketChannel.class)
                .group(bos,worker)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new LoggingHandler(LogLevel.TRACE))
                                // HttpRequestDecoder和HttpResponseEncoder的一个组合，针对http协议进行编解码
                                .addLast(new HttpServerCodec())
                                // 分块向客户端写数据，防止发送大文件时导致内存溢出， channel.write(new ChunkedFile(new File("bigFile.mkv")))
                                .addLast(new ChunkedWriteHandler())
                                // 将HttpMessage和HttpContents聚合到一个完成的 FullHttpRequest或FullHttpResponse中,具体是FullHttpRequest对象还是FullHttpResponse对象取决于是请求还是响应
                                // 需要放到HttpServerCodec这个处理器后面
                                .addLast(new HttpObjectAggregator(10240))
                                // webSocket 数据压缩扩展，当添加这个的时候WebSocketServerProtocolHandler的第三个参数需要设置成true
//                                    .addLast(new WebSocketServerCompressionHandler())
                                // 服务器端向外暴露的 web socket 端点，当客户端传递比较大的对象时，maxFrameSize参数的值需要调大
                                .addLast(new WebSocketServerProtocolHandler("/ws"))
                                .addLast(new SimpleChannelInboundHandler<TextWebSocketFrame>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
                                        NioSocketChannel channel = (NioSocketChannel) ctx.channel();
                                        channel.parent().close();
                                        System.out.println(msg.content());
                                    }

                                    @Override
                                    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                        // 处理 心跳事件
                                        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
                                            WebSocketServerProtocolHandler.HandshakeComplete  request=(WebSocketServerProtocolHandler.HandshakeComplete) evt;
                                            System.out.println(request.requestUri());
                                        }
                                        super.userEventTriggered(ctx, evt);
                                    }
                                });
                    }
                });
    }


    @PostConstruct
    public void init(){

            channelFuture = srv.bind(3333).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            System.out.println("启动成功");
                        }
                }
            });

    }

    @PreDestroy
    public void destroy(){
        bos.shutdownGracefully();
        worker.shutdownGracefully();
    }


}

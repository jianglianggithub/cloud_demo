package webrtc;

import com.ContextSSLFactory;
import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import javax.net.ssl.SSLEngine;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class NettyWebRtcSvr {

    public static Map<String, Channel> users = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        NioEventLoopGroup b = new NioEventLoopGroup(1);
        NioEventLoopGroup w = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .group(b, w)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        System.out.println("connect");
                        channel.pipeline()
                                .addLast(new HttpServerCodec())

                                .addLast(new HttpObjectAggregator(65536))
                                .addLast(new ChunkedWriteHandler())
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        if (msg instanceof FullHttpRequest) {
                                            cyclicBarrier.await();


                                            FullHttpRequest request = (FullHttpRequest) msg;
                                            //拿到请求地址
                                            String uri = request.uri();
                                            //判断是不是websocket请求，如果是拿出我们传递的参数（我的是token）
                                            String origin = request.headers().get("Origin");
                                            if (null == origin) {
                                                ctx.close();
                                            } else {
                                                if (null != uri && uri.contains("/ws") && uri.contains("?")) {
                                                    String[] uriArray = uri.split("\\?");
                                                    if (null != uriArray && uriArray.length > 1) {
                                                        String[] paramsArray = uriArray[1].split("=");
                                                        if (null != paramsArray && paramsArray.length > 1) {
                                                            String uid = paramsArray[1];
                                                            Attribute<Object> uid1 = ctx.channel().attr(AttributeKey.valueOf("uid"));
                                                            uid1.set(uid);
                                                            users.put(uid, ctx.channel());

                                                        }
                                                    }
                                                    //重新设置请求地址
                                                    request.setUri("/ws");
                                                }
                                            }

                                        }
                                        super.channelRead(ctx, msg);
                                    }
                                })
                                .addLast(new WebSocketServerProtocolHandler("/ws"))
                                .addLast(new SimpleChannelInboundHandler<TextWebSocketFrame>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
                                        ByteBuf content = msg.content();
                                        String msgstr = content.toString(Charset.defaultCharset());
                                        //System.out.println(msgstr);
                                        WebRtcMsg rtcState = JSON.parseObject(msgstr, WebRtcMsg.class);
                                        if (rtcState.getState() == RtcState.answer.state) {
                                            System.out.println("用户1收到 用户2 返回的 answer");

                                            Channel channel1 = users.get("1");
                                            TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(msgstr);
                                            channel1.writeAndFlush(textWebSocketFrame);
                                        } else if (rtcState.getState() == RtcState.offer.state) {
                                            System.out.println("用户1像 用户2 发送 offer");

                                            Channel channel1 = users.get("2");
                                            TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(msgstr);
                                            channel1.writeAndFlush(textWebSocketFrame);
                                        } else if (rtcState.getState() == RtcState.offer_condidate.state) {
                                            System.out.println("用户1像 用户2发送 condidate");
                                            Channel channel1 = users.get("2");
                                            TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(msgstr);
                                            channel1.writeAndFlush(textWebSocketFrame);
                                        }
                                    }


                                });
                    }
                });

        ChannelFuture future = bootstrap.bind(2333).sync();
        future.channel().closeFuture().sync();

    }
}

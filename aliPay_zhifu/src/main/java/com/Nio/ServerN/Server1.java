package com.Nio.ServerN;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class Server1 {
    public static void main(String[] args) throws Exception{
        Selector open = Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(2333));
        server.register(open, SelectionKey.OP_ACCEPT);

        while (true) {
            open.select();
            Set<SelectionKey> selectionKeys = open.selectedKeys();
            for (Iterator<SelectionKey> iterator = selectionKeys.iterator(); iterator.hasNext();){
                SelectionKey next = iterator.next();
                if (next.interestOps() == SelectionKey.OP_ACCEPT) {
                    ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    iterator.remove();
                    new ReadEventHadnle().regist(accept).start();
                    System.out.println("将 客户端链接注册到选择器中了");

                }
//                else if (next.interestOps() == SelectionKey.OP_READ) {
//                    SocketChannel channel = (SocketChannel) next.channel();
//                    ByteBuffer allocate = ByteBuffer.allocate(1024);
//                    channel.read(allocate);
//                    allocate.flip();
//                    byte[] bytes=new byte[allocate.remaining()];
//                    allocate.get(bytes);
//                    System.out.println("接收到 客户端 发送得消息 " + new String(bytes));
//                }

            }

        }
    }
}

class ReadEventHadnle extends Thread{


    private Selector selector;
    private ByteBuffer buffer=ByteBuffer.allocate(1024);
    private SocketChannel socketChannel;
    public ReadEventHadnle(){
        try {
            selector=Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ReadEventHadnle regist(SocketChannel socketChannel) {
        try {
            this.socketChannel=socketChannel;
            socketChannel.register( selector,SelectionKey.OP_READ  );
            socketChannel.register( selector,SelectionKey.OP_WRITE );

        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select();
                System.out.println(selector.selectedKeys().size()+" is  selectkey size ");

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    // 监听写事件触发的话 只要 socket 缓冲区中 可写的话 那么会一直返回 写事件的selectKey为触发。
                    if (next.interestOps() == SelectionKey.OP_WRITE) {
                        System.out.println("写事件触发");
                    }else {
                        socketChannel.read(buffer);
                        buffer.flip();
                        byte[] bytes=new byte[buffer.remaining()];
                        System.out.println("服务端接收到的msg"+new String(bytes));
                        buffer.clear();
                    }
                    System.out.println(selector.selectedKeys().size()+"selectKey长度");
                    iterator.remove();
                }



            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}

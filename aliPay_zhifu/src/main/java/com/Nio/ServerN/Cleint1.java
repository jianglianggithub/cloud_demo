package com.Nio.ServerN;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

public class Cleint1 {
    public static void main(String[] args) throws Exception {
        SocketChannel open = SocketChannel.open();
        // 当用了选择器后才 有必要用 非阻塞否则没必要用 如果不用选择器直接用阻塞就行了


        boolean connect = open.connect(new InetSocketAddress("127.0.0.1", 2333));
        System.out.println(connect);


        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            open.write(ByteBuffer.wrap(next.getBytes()));
        }
    }
}

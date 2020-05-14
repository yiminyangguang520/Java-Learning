package com.lee.example.restservice.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author lee
 * @date 2020-03-26
 */
public class Client {

  public static void main(String[] args) throws Exception {
    SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9000));
    socketChannel.configureBlocking(false);

    ByteBuffer buffer = ByteBuffer.allocate(1024);
    String msg = "Hello, World!";
    buffer.put(msg.getBytes());
    buffer.flip();
    socketChannel.write(buffer);

    socketChannel.close();
  }
}

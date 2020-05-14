package com.lee.example.restservice.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lee
 * @date 2020-03-26
 */
@Deprecated
public class ChatServer {

  public static void main(String[] args) {
    try {
      Selector selector = Selector.open();

      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.configureBlocking(false);
      serverSocketChannel.bind(new InetSocketAddress(9001));
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

      while (true) {
        selector.select(1000);

        Set<SelectionKey> selectedKeys = selector.selectedKeys();

        Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

        while (keyIterator.hasNext()) {

          SelectionKey key = keyIterator.next();

          if (key.isAcceptable()) {
            // a connection was accepted by a ServerSocketChannel.
            doAccept(key, selector);
          } else if (key.isConnectable()) {
            // a connection was established with a remote server.

          } else if (key.isReadable()) {
            // a channel is ready for reading
            doRead(key, selector);
          } else if (key.isWritable()) {
            // a channel is ready for writing
            doWrite(key, selector);
          }

          keyIterator.remove();
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void doAccept(SelectionKey key, Selector selector) throws IOException {
    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
    SocketChannel socketChannel = serverSocketChannel.accept();
    socketChannel.configureBlocking(false);
    socketChannel.register(selector, SelectionKey.OP_READ);
  }

  private static void doRead(SelectionKey key, Selector selector) throws IOException {
    SocketChannel socketChannel = (SocketChannel) key.channel();
    ByteBuffer readBuffer = ByteBuffer.allocate(48);
    int bytesRead = socketChannel.read(readBuffer);
    if (bytesRead > 0) {
      readBuffer.flip();
      byte[] bytes = new byte[readBuffer.remaining()];
      readBuffer.get(bytes);
      String body = new String(bytes, StandardCharsets.UTF_8);
      System.out.println("【客户端说】：" + body);

      socketChannel.register(selector, SelectionKey.OP_WRITE);
    }
  }

  private static void doWrite(SelectionKey key, Selector selector) throws IOException {
    SocketChannel socketChannel = (SocketChannel) key.channel();
    String resp = "收到，现在是" + LocalDateTime.now();
    byte[] bytes2 = resp.getBytes();
    ByteBuffer writeBuffer = ByteBuffer.allocate(bytes2.length);
    writeBuffer.put(bytes2);
    writeBuffer.flip();
    socketChannel.write(writeBuffer);
    socketChannel.register(selector, SelectionKey.OP_READ);
  }
}

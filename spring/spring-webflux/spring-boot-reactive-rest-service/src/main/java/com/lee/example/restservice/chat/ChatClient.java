package com.lee.example.restservice.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author lee
 * @date 2020-03-26
 */
@Deprecated
public class ChatClient {

  public static void main(String[] args) throws IOException {
    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

    Selector selector = Selector.open();

    SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9001));
    socketChannel.configureBlocking(false);
//        socketChannel.connect(new InetSocketAddress("127.0.0.1",9001));

    socketChannel.register(selector, SelectionKey.OP_CONNECT);

    writeBuffer.put("Hello".getBytes());
    writeBuffer.flip();
    socketChannel.write(writeBuffer);

    Scanner scanner = new Scanner(System.in);

    while (true) {
      selector.select(1000);

      Set<SelectionKey> selectedKeys = selector.selectedKeys();

      Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

      while (keyIterator.hasNext()) {

        SelectionKey key = keyIterator.next();

        if (key.isAcceptable()) {
          // a connection was accepted by a ServerSocketChannel.

        } else if (key.isConnectable()) {
          // a connection was established with a remote server.

        } else if (key.isReadable()) {
          // a channel is ready for reading

          SocketChannel sc = (SocketChannel) key.channel();
          int len = sc.read(readBuffer);
          if (len > 0) {
            readBuffer.flip();
            byte[] bytes = new byte[readBuffer.remaining()];
            readBuffer.get(bytes);
            String body = new String(bytes, "UTF-8");
            System.out.println("【服务端说】：" + body);
          }
          String message = scanner.nextLine();
          readBuffer.put(message.getBytes());
          sc.write(writeBuffer);

          sc.register(selector, SelectionKey.OP_WRITE);
        } else if (key.isWritable()) {
          // a channel is ready for writing

          SocketChannel sc = (SocketChannel) key.channel();
          System.out.println("Please Input: ");
          String message = scanner.nextLine();
          writeBuffer.put(message.getBytes());
          sc.write(writeBuffer);

          sc.register(selector, SelectionKey.OP_READ);
        }

        keyIterator.remove();
      }
    }
  }
}

package com.lee.example.restservice.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lee
 * @date 2020-03-26
 */
public class Server {

  public static void main(String[] args) throws Exception {
    //  创建一个Selector
    Selector selector = Selector.open();

    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.configureBlocking(false);
    serverSocketChannel.bind(new InetSocketAddress(9000));

    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    while (true) {
      selector.select();

      Set<SelectionKey> selectedKeys = selector.selectedKeys();
      Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
      while (keyIterator.hasNext()) {
        SelectionKey key = keyIterator.next();
        if (key.isAcceptable()) {
          // a connection was accepted by a ServerSocketChannel.

          System.out.println(1);
          ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
          SocketChannel sc = ssc.accept();
          sc.configureBlocking(false);
          sc.register(selector, SelectionKey.OP_READ);
        } else if (key.isConnectable()) {
          // a connection was established with a remote server.
        } else if (key.isReadable()) {
          // a channel is ready for reading

          System.out.println(2);
          SocketChannel socketChannel = (SocketChannel) key.channel();
          ByteBuffer buffer = ByteBuffer.allocate(1024);
          int len;
          while ((len = socketChannel.read(buffer)) != -1) {
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, len));
          }

          socketChannel.close();
        } else {
          key.isWritable();// a channel is ready for writing
        }

        keyIterator.remove();
      }
    }
  }
}

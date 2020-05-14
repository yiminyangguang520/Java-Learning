package com.lee.example.restservice.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lee
 * @date 2020-03-26
 */
public class Hello {

  public static void main(String[] args) throws Exception {
    FileInputStream fis = new FileInputStream("/Users/chengjiansheng/Desktop/data.txt");
    FileChannel channel = fis.getChannel();

    ByteBuffer buffer = ByteBuffer.allocate(10);

    while (channel.read(buffer) != -1) {
      buffer.flip();
      while (buffer.hasRemaining()) {
        System.out.print((char) buffer.get());
      }
      buffer.clear();
    }

    channel.close();
    fis.close();
  }
}

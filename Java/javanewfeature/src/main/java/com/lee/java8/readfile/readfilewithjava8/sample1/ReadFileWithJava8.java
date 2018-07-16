package com.lee.java8.readfile.readfilewithjava8.sample1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author litz-a
 */
public class ReadFileWithJava8 {

  public static void main(String[] args) {
    String filePath = "C://readfile/input.txt";

    try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
      stream.forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
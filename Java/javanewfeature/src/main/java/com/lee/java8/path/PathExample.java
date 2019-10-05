package com.lee.java8.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author bruce
 */
public class PathExample {

  public static void main(String[] args) throws IOException {

    Path start = Paths.get("/Users/bruce/code");
    try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
      List<String> collect = stream
        .map(String::valueOf)
        .sorted()
        .collect(Collectors.toList());

      collect.forEach(System.out::println);
    }

    String filePath = "C:\\data\\demo\\sample.txt";
    try (Stream<String> lines = Files.lines(Paths.get((filePath)))) {
      lines.forEach(System.out::println);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}

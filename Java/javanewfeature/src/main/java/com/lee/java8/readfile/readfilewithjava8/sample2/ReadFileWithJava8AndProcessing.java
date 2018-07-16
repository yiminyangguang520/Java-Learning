package com.lee.java8.readfile.readfilewithjava8.sample2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author litz-a
 */
public class ReadFileWithJava8AndProcessing {

  public static void main(String[] args) {
    String filePath = "C://readfile/input.txt";
    List<String> list = new ArrayList<>();

    try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
      //1. filter line 1
      //2. get end character
      //3. convert stream into a List
      list = stream
          .filter(line -> !line.endsWith("1"))
          .map(line -> line.substring(4, 5))
          .collect(Collectors.toList());


    } catch (IOException e) {
      e.printStackTrace();
    }

    // process result List
    list.forEach(System.out::println);
  }
}
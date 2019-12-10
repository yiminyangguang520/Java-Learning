package com.lee.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * @author bruce
 */
public class CustomerCollectorAction {

  public static void main(String[] args) {
    Collector<String, List<String>, List<String>> collector = new ToListCollector<>();

    List<String> result = Arrays.asList("Alex", "Wang", "Hello", "Lambda", "Collector", "Java 8", "Stream")
        .parallelStream()
        .filter(s -> s.length() >= 5)
        .collect(collector);

    System.out.println(result);
  }
}

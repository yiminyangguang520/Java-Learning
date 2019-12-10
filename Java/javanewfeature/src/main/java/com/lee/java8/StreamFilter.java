package com.lee.java8;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author lee
 * @date 2016/10/20
 */
public class StreamFilter {

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 7, 1);
    List<Integer> result = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
    System.out.println(result);

    result = list.stream().distinct().collect(Collectors.toList());
    System.out.println(result);

    result = list.stream().skip(50).collect(Collectors.toList());
    System.out.println(result);

    result = list.stream().limit(50).collect(Collectors.toList());
    System.out.println(result);

    list.forEach(System.out::println);

    for (int i : list) {
      System.out.println(i);
    }
  }
}

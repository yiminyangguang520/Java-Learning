package com.lee.java8;

import static java.util.stream.Collectors.groupingBy;

import com.lee.java8.model.Apple;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lee
 */
public class CollectorIntroduce {

  public static void main(String[] args) {
    List<Apple> list = Arrays.asList(new Apple("green", 150)
        , new Apple("yellow", 120)
        , new Apple("green", 170)
        , new Apple("green", 150)
        , new Apple("yellow", 120)
        , new Apple("green", 170));

    List<Apple> greenList = list.stream().filter(a -> "green".equals(a.getColor())).collect(Collectors.toList());
    Optional.ofNullable(greenList).ifPresent(System.out::println);
    Optional.ofNullable(groupByNormal(list)).ifPresent(System.out::println);
    System.out.println("===================================================");
    Optional.ofNullable(groupByFunction(list)).ifPresent(System.out::println);
    System.out.println("===================================================");
    Optional.ofNullable(groupByCollector(list)).ifPresent(System.out::println);
  }

  private static Map<String, List<Apple>> groupByNormal(List<Apple> apples) {
    Map<String, List<Apple>> map = new HashMap<>(4);
    for (Apple a : apples) {
      List<Apple> list = map.computeIfAbsent(a.getColor(), k -> new ArrayList<>());
      list.add(a);
    }
    return map;
  }

  private static Map<String, List<Apple>> groupByFunction(List<Apple> apples) {
    Map<String, List<Apple>> map = new HashMap<>();
    apples.parallelStream().forEach(a -> {
      List<Apple> colorList = Optional.ofNullable(map.get(a.getColor())).orElseGet(() -> {
        List<Apple> list = new ArrayList<>();
        map.put(a.getColor(), list);
        return list;
      });
      colorList.add(a);
    });
    return map;
  }

  private static Map<String, List<Apple>> groupByCollector(List<Apple> apples) {
    return apples.parallelStream().collect(groupingBy(Apple::getColor));
  }
}

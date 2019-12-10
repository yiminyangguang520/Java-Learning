package com.lee.java8;

import static com.lee.java8.CollectorsAction.menu;

import com.lee.java8.model.Dish;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

/**
 * @author bruce
 */
public class CollectorsAction2 {

  public static void main(String[] args) {
    testGroupingByConcurrentWithFunction();
    testGroupingByConcurrentWithFunctionAndCollector();
    testGroupingByConcurrentWithFunctionAndSupplierAndCollector();
    testJoining();
    testJoiningWithDelimiter();
    testJoiningWithDelimiterAndPrefixAndSuffix();
    testMapping();
    testMaxBy();
    testMinBy();
  }

  private static void testGroupingByConcurrentWithFunction() {
    System.out.println("testGroupingByConcurrentWithFunction");

    ConcurrentMap<Dish.Type, List<Dish>> collect = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType));
    Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
    Optional.of(collect).ifPresent(System.out::println);
  }


  private static void testGroupingByConcurrentWithFunctionAndCollector() {
    System.out.println("testGroupingByConcurrentWithFunctionAndCollector");
    ConcurrentMap<Dish.Type, Double> collect = menu.stream()
        .collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
    Optional.ofNullable(collect).ifPresent(System.out::println);
  }

  private static void testGroupingByConcurrentWithFunctionAndSupplierAndCollector() {
    System.out.println("testGroupingByConcurrentWithFunctionAndSupplierAndCollector");
    ConcurrentMap<Dish.Type, Double> collect = menu.stream()
        .collect(Collectors.groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new, Collectors.averagingInt(Dish::getCalories)));
    Optional.of(collect.getClass()).ifPresent(System.out::println);
    Optional.of(collect).ifPresent(System.out::println);
  }

  private static void testJoining() {
    System.out.println("testJoining");
    Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining()))
        .ifPresent(System.out::println);
  }

  private static void testJoiningWithDelimiter() {
    System.out.println("testJoiningWithDelimiter");
    Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining(",")))
        .ifPresent(System.out::println);
  }

  private static void testJoiningWithDelimiterAndPrefixAndSuffix() {
    System.out.println("testJoiningWithDelimiterAndPrefixAndSuffix");
    Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining(",", "Names[", "]")))
        .ifPresent(System.out::println);
  }

  private static void testMapping() {
    System.out.println("testMapping");
    Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining(",")))
        .ifPresent(System.out::println);
  }

  private static void testMaxBy() {
    System.out.println("testMaxBy");
    menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)))
        .ifPresent(System.out::println);
  }

  private static void testMinBy() {
    System.out.println("testMinBy");
    menu.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)))
        .ifPresent(System.out::println);
  }
}

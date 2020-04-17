package com.lee.java8;

import static com.lee.java8.CollectorsAction.menu;

import com.lee.java8.model.Dish;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @author min
 */
public class CollectorsAction3 {

  public static void main(String[] args) {
    testPartitioningByWithPredicate();
    testPartitioningByWithPredicateAndCollector();
    testReducingBinaryOperator();
    testReducingBinaryOperatorAndIdentiy();
    testReducingBinaryOperatorAndIdentiyAndFunction();
    testSummarizingDouble();
    testSummarizingLong();
    testSummarizingInt();
  }

  private static void testPartitioningByWithPredicate() {
    System.out.println("testPartitioningByWithPredicate");
    Map<Boolean, List<Dish>> collect = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
    Optional.of(collect).ifPresent(System.out::println);

  }

  private static void testPartitioningByWithPredicateAndCollector() {
    System.out.println("testPartitioningByWithPredicateAndCollector");
    Map<Boolean, Double> collect = menu.stream()
        .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingInt(Dish::getCalories)));
    Optional.of(collect).ifPresent(System.out::println);
  }

  private static void testReducingBinaryOperator() {
    System.out.println("testReducingBinaryOperator");
    menu.stream().collect(
        Collectors.reducing(
            BinaryOperator.maxBy(
                Comparator.comparingInt(Dish::getCalories)
            )
        )
    ).ifPresent(System.out::println);
  }

  private static void testReducingBinaryOperatorAndIdentiy() {
    System.out.println("testReducingBinaryOperatorAndIdentiy");
    Integer result = menu.stream()
        .map(Dish::getCalories).reduce(0, Integer::sum);
    System.out.println(result);
  }

  private static void testReducingBinaryOperatorAndIdentiyAndFunction() {
    System.out.println("testReducingBinaryOperatorAndIdentiyAndFunction");
    Integer result = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
    System.out.println(result);
  }

  private static void testSummarizingDouble() {
    System.out.println("testSummarizingDouble");
    Optional.of(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
        .ifPresent(System.out::println);
  }

  private static void testSummarizingLong() {
    System.out.println("testSummarizingLong");
    Optional.of(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories)))
        .ifPresent(System.out::println);
  }

  private static void testSummarizingInt() {
    System.out.println("testSummarizingLong");
    Optional.of(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
        .ifPresent(System.out::println);
  }
}

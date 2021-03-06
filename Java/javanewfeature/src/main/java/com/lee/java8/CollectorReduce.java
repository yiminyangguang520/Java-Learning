package com.lee.java8;

import static java.util.stream.Collectors.toList;

import com.lee.java8.model.Dish;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author bruce
 */
public class CollectorReduce {

  public static void main(String[] args) {
    List<Dish> menu = Arrays.asList(
        new Dish("pork", false, 800, Dish.Type.MEAT),
        new Dish("beef", false, 700, Dish.Type.MEAT),
        new Dish("chicken", false, 400, Dish.Type.MEAT),
        new Dish("french fries", true, 530, Dish.Type.OTHER),
        new Dish("rice", true, 350, Dish.Type.OTHER),
        new Dish("season fruit", true, 120, Dish.Type.OTHER),
        new Dish("pizza", true, 550, Dish.Type.OTHER),
        new Dish("prawns", false, 300, Dish.Type.FISH),
        new Dish("salmon", false, 450, Dish.Type.FISH));

    long count = menu.stream().filter(Dish::isVegetarian).count();
    System.out.println("the count of vegetarian dish is " + count);

    Optional<Integer> maxCalory = menu.stream().map(Dish::getCalories).reduce(Integer::max);
    maxCalory.ifPresent(calory -> System.out.println("the max calory of dish is " + calory));

    Optional<Dish> maxCalories = menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
    maxCalories.ifPresent(System.out::println);

    Optional<Dish> maxCaloriesCollect = menu.stream().max(Comparator.comparingInt(Dish::getCalories));
    maxCaloriesCollect.ifPresent(System.out::println);

    Integer collect1 = menu.stream().collect(Collectors.collectingAndThen(toList(), List::size));
    System.out.println(collect1);

    Map<Dish.Type, List<Dish>> collect2 = menu.stream().collect(Collectors.groupingBy(Dish::getType));
    System.out.println(collect2);

    Map<Dish.Type, Double> collect3 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
    System.out.println(collect3);


  }
}

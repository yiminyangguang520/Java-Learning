package com.lee.java8;

import com.lee.java8.modle.Apple;
import com.lee.java8.modle.ComplexApple;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lee
 * @date 2016/10/16
 */
public class MethodReference {

  public static void main(String[] args) {

    Consumer<String> consumer = System.out::println;
    useConsumer(consumer, "Hello Alex");

    useConsumer(System.out::println, "Hello Alex");

    useConsumer(System.out::println, "Hello lee");

    List<Apple> list = Arrays.asList(new Apple("abc", 123), new Apple("Green", 110), new Apple("red", 123));

    System.out.println(list);

    list.sort(Comparator.comparing(Apple::getColor));

    System.out.println(list);

    list.stream().forEach(System.out::println);

    System.out.println("==========================");
    list.stream().forEach(System.out::println);

    int value = Integer.parseInt("123");

    Function<String, Integer> f = Integer::parseInt;

    Integer result = f.apply("123");
    System.out.println(result);

    BiFunction<String, Integer, Character> f2 = String::charAt;
    Character c = f2.apply("hello", 2);
    System.out.println(c);

    String string = "Hello";
    Function<Integer, Character> f3 = string::charAt;
    Character c2 = f3.apply(4);
    System.out.println(c2);

    Supplier<String> supplier = String::new;

    String s = supplier.get();
    System.out.println(s);

    BiFunction<String, Long, Apple> appleFuntion = Apple::new;

    Apple apple = appleFuntion.apply("red", 100L);
    System.out.println(apple);

    ThreeFunction<String, Long, String, ComplexApple> threeFunction = ComplexApple::new;
    ComplexApple complexApple = threeFunction.apply("Green", 123L, "FuShi");
    System.out.println(complexApple);

    List<Apple> list2 = Arrays.asList(new Apple("abc", 123), new Apple("Green", 110), new Apple("red", 123));

    System.out.println(list2);
    list2.sort(Comparator.comparing(Apple::getColor));
    System.out.println(list2);
  }

  private static <T> void useConsumer(Consumer<T> consumer, T t) {
    consumer.accept(t);
    consumer.accept(t);
  }
}

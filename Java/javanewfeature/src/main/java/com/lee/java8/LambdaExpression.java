package com.lee.java8;

import com.lee.java8.model.Apple;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author lee
 * @date 2016/10/12
 */
public class LambdaExpression {

  public static void main(String[] args) {

    Comparator<Apple> byColor = Comparator.comparing(Apple::getColor);

    List<Apple> list = Collections.emptyList();

    list.sort(byColor);

    Comparator<Apple> byColor2 = Comparator.comparing(Apple::getColor);

    Function<String, Integer> flambda = s -> s.length();

    Predicate<Apple> p = (Apple a) -> a.getColor().equals("green");

    Runnable r = () -> {
    };

    Function<Apple, Boolean> f = (a) -> a.getColor().equals("green");

  }
}

package com.lee.java8;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author bruce
 */
public class CompletableFutureInAction1 {

  private final static Random RANDOM = new Random(System.currentTimeMillis());

  public static void main(String[] args) {
    //supplyAsync
    CompletableFuture<Double> completableFuture = new CompletableFuture<>();
    new Thread(() -> {
      double value = get();
      completableFuture.complete(value);
    }).start();

    System.out.println("===no===block....");

    completableFuture.whenComplete((v, t) -> {
      Optional.ofNullable(v).ifPresent(System.out::println);
      Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
    });
  }

  static double get() {
    try {
      Thread.sleep(RANDOM.nextInt(10000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    double result = RANDOM.nextDouble();
    System.out.println(result);
    return result;
  }
}

package com.lee.java8.concurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author rajeevkumarsingh
 * @date 11/05/17
 */


public class SynchronizedMethodExample {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    SynchronizedCounter synchronizedCounter = new SynchronizedCounter();

    for (int i = 0; i < 1000; i++) {
      executorService.submit(() -> synchronizedCounter.increment());
    }

    executorService.shutdown();
    executorService.awaitTermination(60, TimeUnit.SECONDS);

    System.out.println("Final count is : " + synchronizedCounter.getCount());
  }

  static class SynchronizedCounter {

    private int count = 0;

    // Synchronized Method
    public synchronized void increment() {
      System.out.println(Thread.currentThread().getName());
      count = count + 1;
    }

    public int getCount() {
      return count;
    }
  }
}

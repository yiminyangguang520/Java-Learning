package com.lee.java8.concurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author rajeevkumarsingh
 * @date 11/05/17
 */
public class RaceConditionExample {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = new ThreadPoolExecutor(10, 10,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>());

    Counter counter = new Counter();

    for (int i = 0; i < 1000; i++) {
      executorService.submit(() -> counter.increment());
    }

    executorService.shutdown();
    executorService.awaitTermination(60, TimeUnit.SECONDS);

    System.out.println("Final count is : " + counter.getCount());
  }

  static class Counter {

    private int count = 0;

    public void increment() {
      count = count + 1;
    }

    public int getCount() {
      return count;
    }
  }
}

package com.lee.java8.concurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author rajeevkumarsingh
 * @date 11/05/17
 */
public class SynchronizedMethodExample {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = new ThreadPoolExecutor(10, 10,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>());

    SynchronizedCounter synchronizedCounter = new SynchronizedCounter();

    for (int i = 0; i < 1000; i++) {
      executorService.submit(synchronizedCounter::increment);
    }

    executorService.shutdown();
    executorService.awaitTermination(60, TimeUnit.SECONDS);

    System.out.println("Final count is : " + synchronizedCounter.getCount());
  }

  static class SynchronizedCounter {

    private int count = 0;

    // Synchronized Method
    synchronized void increment() {
      System.out.println(Thread.currentThread().getName());
      count = count + 1;
    }

    public int getCount() {
      return count;
    }
  }
}

package com.lee.java8.concurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author rajeevkumarsingh
 * @date 11/05/17
 */
public class SynchronizedBlockExample {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = new ThreadPoolExecutor(10, 10,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>());

    FineGrainedSynchronizedCounter counter = new FineGrainedSynchronizedCounter();

    for (int i = 0; i < 1000; i++) {
      executorService.submit(counter::increment);
    }

    executorService.shutdown();
    executorService.awaitTermination(60, TimeUnit.SECONDS);

    System.out.println("Final count is " + counter.getCount());
  }

  static class FineGrainedSynchronizedCounter {

    private int count = 0;

    void increment() {
      // Synchronized Block
      synchronized (this) {
        count = count + 1;
      }
    }

    public int getCount() {
      return count;
    }
  }
}

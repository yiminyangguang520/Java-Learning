package com.lee.java8.concurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 * @author min
 */
public class VolatileKeywordExample {

  private static volatile boolean sayHello = false;

  public static void main(String[] args) throws InterruptedException {
    ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(), new CallerRunsPolicy());

    singleThreadPool.submit(() -> {
      while (!sayHello) {

      }

      System.out.println("Hello World!");

      while (sayHello) {

      }

      System.out.println("Good Bye!");
    });

    singleThreadPool.shutdown();
    singleThreadPool.awaitTermination(60, TimeUnit.SECONDS);
  }
}

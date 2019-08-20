package com.lee.java8.concurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author litz-a
 */
public class MemoryConsistencyErrorExample {

  private static boolean sayHello = false;

  public static void main(String[] args) throws InterruptedException {
    ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>());

    singleThreadPool.submit(() -> {
      while (!sayHello) {

      }

      System.out.println("Hello World!");

      while (sayHello) {

      }

      System.out.println("Good Bye!");
    });

    Thread.sleep(1000);
    System.out.println("Say Hello..");
    sayHello = true;

    Thread.sleep(1000);
    System.out.println("Say Bye..");
    sayHello = false;

    singleThreadPool.shutdown();
  }
}

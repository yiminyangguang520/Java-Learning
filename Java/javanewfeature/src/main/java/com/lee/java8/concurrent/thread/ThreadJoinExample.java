package com.lee.java8.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author rajeevkumarsingh
 * @date 08/05/17
 */

public class ThreadJoinExample {

  public static void main(String[] args) throws InterruptedException {
    // Create Thread 1
    ExecutorService singleThread1 = new ThreadPoolExecutor(1, 1,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new CallerRunsPolicy());

    singleThread1.submit(() -> {
      System.out.println("Entered Thread 1");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
      System.out.println("Exiting Thread 1");
    });


    System.out.println("Starting Thread 1");
    System.out.println("Waiting for Thread 1 to complete");
    singleThread1.shutdown();
    singleThread1.awaitTermination(1000, TimeUnit.MILLISECONDS);

    System.out.println("Waited enough! Starting Thread 2 now");

    // Create Thread 2
    ExecutorService singleThread2 = new ThreadPoolExecutor(1, 1,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new CallerRunsPolicy());
    singleThread2.submit(() -> {
      System.out.println("Entered Thread 2");
      try {
        Thread.sleep(4000);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
      System.out.println("Exiting Thread 2");
    });

    singleThread2.shutdown();
  }
}

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
public class ThreadSleepExample {

  public static void main(String[] args) {
    System.out.println("Inside : " + Thread.currentThread().getName());

    String[] messages = {"If I can stop one heart from breaking,",
        "I shall not live in vain.",
        "If I can ease one life the aching,",
        "Or cool one pain,",
        "Or help one fainting robin",
        "Unto his nest again,",
        "I shall not live in vain"};

    Runnable runnable = () -> {
      System.out.println("Inside : " + Thread.currentThread().getName());

      for (String message : messages) {
        System.out.println(message);
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          throw new IllegalStateException(e);
        }
      }
    };


    ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new CallerRunsPolicy());

    singleThreadPool.submit(runnable);
    singleThreadPool.shutdown();
  }
}

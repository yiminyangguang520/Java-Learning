package com.lee.java8.concurrent.threadpool.feature;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 * @author litz-a
 */
public class FutureInAction2 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    ExecutorService executorService = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new CallerRunsPolicy());
    Future<String> future = executorService.submit(() -> {
      try {
        Thread.sleep(10000L);
        return "I am finished.";
      } catch (InterruptedException e) {
        return "I am Error.";
      }
    });

    // String value = future.get(10, TimeUnit.MICROSECONDS);
    while (!future.isDone()) {
      Thread.sleep(10);
    }

    System.out.println(future.get());

    executorService.shutdown();
  }
}
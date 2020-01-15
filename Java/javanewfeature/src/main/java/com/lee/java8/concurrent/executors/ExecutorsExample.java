package com.lee.java8.concurrent.executors;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author rajeevkumarsingh
 * @date 09/05/17
 */
public class ExecutorsExample {

  public static void main(String[] args) {
    System.out.println("Inside : " + Thread.currentThread().getName());

    System.out.println("Creating Executor Service with a thread pool of Size 2");
    ExecutorService executorService = new ThreadPoolExecutor(2, 2,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new CallerRunsPolicy());

    Runnable task1 = () -> {
      System.out.println("Executing Task1 inside : " + Thread.currentThread().getName());
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException ex) {
        throw new IllegalStateException(ex);
      }
    };

    Runnable task2 = () -> {
      System.out.println("Executing Task2 inside : " + Thread.currentThread().getName());
      try {
        TimeUnit.SECONDS.sleep(4);
      } catch (InterruptedException ex) {
        throw new IllegalStateException(ex);
      }
    };

    Runnable task3 = () -> {
      System.out.println("Executing Task3 inside : " + Thread.currentThread().getName());
      try {
        TimeUnit.SECONDS.sleep(100);
      } catch (InterruptedException ex) {
        throw new IllegalStateException(ex);
      }
    };

    System.out.println("Submitting the tasks for execution...");
    executorService.submit(task1);
    executorService.submit(task2);
    executorService.submit(task3);

    // 当调用了shutdown()方法时，便进入关闭状态，此时意味着 ExecutorService不再接受新的任务，
    // 但它还在执行已经提交了的任务，当已经提交了的任务执行完后，便到达终止状态。如果不调用 shutdown()方法，
    // ExecutorService 会一直处在运行状态，不断接收新的任务，执行新的任务，服务器端一般不需要关闭它，保持一直运行即可
    executorService.shutdown();
  }
}

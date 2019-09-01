package com.lee.java8.concurrent.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/**
 * @author bruce
 */
public class InvokeAllExample {

  public static void main(String[] args) throws InterruptedException {

    ExecutorService exec = new ThreadPoolExecutor(10, 10,
    0L, TimeUnit.MILLISECONDS,
    new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new AbortPolicy());

    List<Callable<Integer>> tasks = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      Callable<Integer> task = () -> {
        int ran = new Random().nextInt(1000);
        Thread.sleep(ran);
        System.out.println(Thread.currentThread().getName() + " 休息了 " + ran);
        return ran;
      };

      tasks.add(task);
    }

    long s = System.currentTimeMillis();

    List<Future<Integer>> results = exec.invokeAll(tasks);

    System.out.println("执行任务消耗了 ：" + (System.currentTimeMillis() - s) + "毫秒");

    for (Future<Integer> result : results) {
      try {
        System.out.println(result.get());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    exec.shutdown();

  }
}

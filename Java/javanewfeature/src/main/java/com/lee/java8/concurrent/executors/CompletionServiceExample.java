package com.lee.java8.concurrent.executors;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/**
 * CompletionService将Executor（线程池）和BlockingQueue（阻塞队列）结合在一起，同时使用Callable作为任务的基本单元，
 * 整个过程就是生产者不断把Callable任务放入阻塞对了，Executor作为消费者不断把任务取出来执行，并返回结果
 *
 * 优势：
 * a、阻塞队列防止了内存中排队等待的任务过多，造成内存溢出（毕竟一般生产者速度比较快，比如爬虫准备好网址和规则，就去执行了，执行起来（消费者）还是比较慢的）
 * b、CompletionService可以实现，哪个任务先执行完成就返回，而不是按顺序返回，这样可以极大的提升效率；
 * 1、CompletionService ： Executor + BlockingQueue
 *
 * 将Executor和BlockingQueue功能融合在一起，可以将Callable的任务提交给它来执行，然后使用take()方法获得已经完成的结果
 *
 * @author litz-a
 */
public class CompletionServiceExample {

  public static void main(String[] args) {

    // 内部维护11个线程的线程池
    ExecutorService exec = new ThreadPoolExecutor(11, 11,
      0L, TimeUnit.MILLISECONDS,
      new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new AbortPolicy());


    // 容量为10的阻塞队列
    final BlockingQueue<Future<Integer>> queue = new LinkedBlockingDeque<>(10);

    // 实例化CompletionService
    final CompletionService<Integer> completionService = new ExecutorCompletionService<>(exec, queue);

    // 模拟瞬间产生10个任务，且每个任务执行时间不一致
    for (int i = 0; i < 10; i++) {
      completionService.submit(() -> {
        int ran = new Random().nextInt(1000);
        Thread.sleep(ran);
        System.out.println(Thread.currentThread().getName() + " 休息了 " + ran);
        return ran;
      });
    }


    // 立即输出结果
    for (int i = 0; i < 10; i++) {
      try {
        // 谁最先执行完成，直接返回
        Future<Integer> f = completionService.take();
        System.out.println(f.get());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }

    exec.shutdown();
  }

}

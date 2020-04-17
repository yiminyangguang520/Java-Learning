package com.lee.java8.concurrent.threadpool.forkjoin;

import java.util.concurrent.ForkJoinPool;

/**
 * @author min
 */
public class ForkJoinPoolTest {

  private static int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

  public static void main(String[] args) {
    System.out.println("result=> " + calc());
    AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length, data);
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    Integer result = forkJoinPool.invoke(task);
    System.out.println("AccumulatorRecursiveTask >>" + result);

    AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, data.length, data);
    forkJoinPool.invoke(action);
    System.out.println("AccumulatorRecursiveAction >>" + AccumulatorRecursiveAction.AccumulatorHelper.getResult());
  }


  private static int calc() {
    int result = 0;
    for (int datum : data) {
      result += datum;
    }
    return result;
  }

}
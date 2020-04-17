package com.lee.java8.concurrent.threadpool.forkjoin;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author min
 */
public class AccumulatorRecursiveAction extends RecursiveAction {

  private final int start;
  private final int end;
  private final int[] data;
  private final int LIMIT = 3;

  public AccumulatorRecursiveAction(int start, int end, int[] data) {
    this.start = start;
    this.end = end;
    this.data = data;
  }

  @Override
  protected void compute() {
    if ((end - start) <= LIMIT) {
      for (int i = start; i < end; i++) {
        AccumulatorHelper.accumulate(data[i]);
      }
    } else {
      int mid = (start + end) / 2;
      AccumulatorRecursiveAction left = new AccumulatorRecursiveAction(start, mid, data);
      AccumulatorRecursiveAction right = new AccumulatorRecursiveAction(mid, end, data);
      left.fork();
      right.fork();
      left.join();
      right.join();
    }
  }

  static class AccumulatorHelper {

    private static final AtomicInteger RESULT = new AtomicInteger(0);

    static void accumulate(int value) {
      RESULT.getAndAdd(value);
    }

    public static int getResult() {
      return RESULT.get();
    }

    static void rest() {
      RESULT.set(0);
    }
  }
}

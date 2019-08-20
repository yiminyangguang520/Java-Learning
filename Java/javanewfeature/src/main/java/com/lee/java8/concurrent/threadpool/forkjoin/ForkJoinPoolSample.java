package com.lee.java8.concurrent.threadpool.forkjoin;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author litz-a
 */
public class ForkJoinPoolSample {

  private static void giveRangeOfLongs_whenSummedInParallel_shouldBeEqualToExpectedTotal()
      throws InterruptedException, ExecutionException {

    long firstNum = 1;
    long lastNum = 1_000_000;

    List<Long> aList = LongStream.rangeClosed(firstNum, lastNum)
        .boxed()
        .collect(Collectors.toList());

    ForkJoinPool customThreadPool = new ForkJoinPool();
    long actualTotal = customThreadPool.submit(() -> aList.parallelStream().reduce(0L, Long::sum)).get();

    System.out.println(actualTotal);
    System.out.println(((lastNum + firstNum) * lastNum / 2));
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    giveRangeOfLongs_whenSummedInParallel_shouldBeEqualToExpectedTotal();
  }
}

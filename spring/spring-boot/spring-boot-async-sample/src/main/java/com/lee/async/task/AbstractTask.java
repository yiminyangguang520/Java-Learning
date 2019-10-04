package com.lee.async.task;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

import java.util.Random;

/**
 * @author bruce
 */
public abstract class AbstractTask {

  private static Random random = new Random();

  public void doTaskOne() throws Exception {
    out.println(Thread.currentThread().getName() + " 开始做任务一");
    long start = currentTimeMillis();
    sleep(random.nextInt(10000));
    long end = currentTimeMillis();
    out.println(Thread.currentThread().getName() + " 完成任务一，耗时：" + (end - start) + "毫秒");
  }

  public void doTaskTwo() throws Exception {
    out.println(Thread.currentThread().getName() + " 开始做任务二");
    long start = currentTimeMillis();
    sleep(random.nextInt(10000));
    long end = currentTimeMillis();
    out.println(Thread.currentThread().getName() + " 完成任务二，耗时：" + (end - start) + "毫秒");
  }

  public void doTaskThree() throws Exception {
    out.println(Thread.currentThread().getName() + " 开始做任务三");
    long start = currentTimeMillis();
    sleep(random.nextInt(10000));
    long end = currentTimeMillis();
    out.println(Thread.currentThread().getName() + " 完成任务三，耗时：" + (end - start) + "毫秒");
  }
}

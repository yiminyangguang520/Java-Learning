package com.lee.dp.proxy.example8;

/**
 *
 * @author PengRong
 * @date 2018/12/25
 */
public class MonitorUtil {

  private static final ThreadLocal<Long> TL = new ThreadLocal<>();

  public static void start() {
    TL.set(System.currentTimeMillis());
  }

  /**
   * 结束时打印耗时
   *
   * @param methodName 方法名
   */
  public static void finish(String methodName) {
    long finishTime = System.currentTimeMillis();
    System.out.println(methodName + "方法执行耗时" + (finishTime - TL.get()) + "ms");
  }

  public static void remove() {
    TL.remove();
  }
}
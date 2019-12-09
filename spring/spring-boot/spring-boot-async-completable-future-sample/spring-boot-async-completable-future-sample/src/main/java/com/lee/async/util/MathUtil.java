package com.lee.async.util;

import java.util.Random;

/**
 * @author bruce
 * @date 2019/10/22
 * @description
 */
public class MathUtil {

  private static Random random = new Random();

  public static int getRdTime(int millseconds) {
    return random.nextInt(millseconds);
  }
}

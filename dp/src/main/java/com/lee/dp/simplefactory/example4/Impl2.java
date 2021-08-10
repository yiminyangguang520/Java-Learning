package com.lee.dp.simplefactory.example4;

/**
 * 对接口的一种实现
 * @author bruce
 */
public class Impl2 implements Api {

  @Override
  public void test1(String s) {
    System.out.println("Now In Impl2. The input s==" + s);
  }
}

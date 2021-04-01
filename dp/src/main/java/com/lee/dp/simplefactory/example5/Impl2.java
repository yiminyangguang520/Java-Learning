package com.lee.dp.simplefactory.example5;

/**
 * 对某个接口的一种实现
 * @author bruce
 */
public class Impl2 implements Api {

  @Override
  public void test1(String s) {
    System.out.println("Now In Impl222222. The input s==" + s);
  }
}

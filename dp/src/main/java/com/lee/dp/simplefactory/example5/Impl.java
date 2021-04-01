package com.lee.dp.simplefactory.example5;

/**
 * 对某个接口的一种实现
 * @author bruce
 */
public class Impl implements Api {

  @Override
  public void test1(String s) {
    System.out.println("Now In Impl. The input s==" + s);
  }
}

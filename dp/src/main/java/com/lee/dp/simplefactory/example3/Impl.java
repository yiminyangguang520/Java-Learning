package com.lee.dp.simplefactory.example3;

/**
 * 对接口的实现
 * @author bruce
 */
public class Impl implements Api {

  @Override
  public void test1(String s) {
    System.out.println("Now In Impl. The input s==" + s);
  }
}

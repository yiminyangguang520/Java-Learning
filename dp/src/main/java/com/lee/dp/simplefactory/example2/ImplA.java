package com.lee.dp.simplefactory.example2;

/**
 * 接口的具体实现对象A
 * @author bruce
 */
public class ImplA implements Api {

  @Override
  public void operation(String s) {
    //实现功能的代码，示意一下
    System.out.println("ImplA s==" + s);
  }
}

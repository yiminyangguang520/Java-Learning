package com.lee.dp.simplefactory.example2;

/**
 * 接口的具体实现对象B
 * @author bruce
 */
public class ImplB implements Api {

  @Override
  public void operation(String s) {
    //实现功能的代码，示意一下
    System.out.println("ImplB s==" + s);
  }
}

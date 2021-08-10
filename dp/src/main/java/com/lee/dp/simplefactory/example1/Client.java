package com.lee.dp.simplefactory.example1;

/**
 * 客户端：测试使用Api接口
 * @author bruce
 */
public class Client {

  public static void main(String[] args) {
    Api api = new Impl();

    api.test1("哈哈，不要紧张，只是个测试而已！");
  }
}

package com.lee.dp.proxy.example7;

/**
 * @author bruce
 */
public class StaticProxyTest {

  public static void main(String[] args) {
    Lion lion = new Lion();
    lion.setName("狮子 小王");
    lion.setRunningSpeed(100);
    /**
     * new 静态代理类，静态代理类在编译前已经创建好了，和动态代理的最大区别点
     */
    Cat proxy = new FeederProxy(lion);
    System.out.println(Thread.currentThread().getName() + " -- " + proxy.eatFood("水牛"));
    proxy.running();
  }
}

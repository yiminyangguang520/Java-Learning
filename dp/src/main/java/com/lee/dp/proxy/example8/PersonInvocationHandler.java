package com.lee.dp.proxy.example8;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author PengRong
 * @date 2018/12/25 PersonInvocationHandler 类 实现InvocationHandler接口，这个类中持有一个被代理对象(委托类)的实例target。
 * 该类别JDK Proxy类回调InvocationHandler接口中有一个invoke方法，当一个代理实例的方法被调用时，代理方法将被编码并分发到InvocationHandler接口的invoke方法执行。
 */
public class PersonInvocationHandler<T> implements InvocationHandler {

  /**
   * 被代理对象引用，invoke 方法里面method 需要使用这个 被代理对象
   */
  T target;

  public PersonInvocationHandler(T target) {
    this.target = target;
  }

  /**
   * 在
   *
   * @param proxy  代表动态生成的 动态代理 对象实例
   * @param method 代表被调用委托类的接口方法，和生成的代理类实例调用的接口方法是一致的，它对应的Method 实例
   * @param args   代表调用接口方法对应的Object参数数组，如果接口是无参，则为null； 对于原始数据类型返回的他的包装类型。
   * @return
   * @throws Throwable
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    // 在转调具体目标对象之前，可以执行一些功能处理
    System.out.println("被动态代理类回调执行, 代理类 proxyClass =" + proxy.getClass()
        + " 方法名: " + method.getName() + "方法. 方法返回类型：" + method.getReturnType()
        + " 接口方法入参数组: " + (args == null ? "null" : Arrays.toString(args)));

    // 代理过程中插入监测方法,计算该方法耗时
    MonitorUtil.start();
    Thread.sleep(1);
    // 调用呗代理对象的真实方法
    Object result = method.invoke(target, args);
    MonitorUtil.finish(method.getName());
    MonitorUtil.remove();
    return result;
  }
}
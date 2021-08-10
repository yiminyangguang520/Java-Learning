package com.lee.dp.simplefactory.example5;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.Cleanup;

/**
 * 工厂类，用来创造Api对象
 *
 * @author bruce
 */
public class Factory {

  /**
   * 具体的创造Api的方法，根据配置文件的参数来创建接口
   *
   * @return 创造好的Api对象
   */
  public static Api createApi() {
    //直接读取配置文件来获取需要创建实例的类

    //至于如何读取Properties还有如何反射这里就不解释了
    Properties p = new Properties();
    try {
      @Cleanup InputStream in = Factory.class.getResourceAsStream("FactoryTest.properties");
      p.load(in);
    } catch (IOException e) {
      System.out.println("装载工厂配置文件出错了，具体的堆栈信息如下：");
      e.printStackTrace();
    }

    //用反射去创建，那些例外处理等完善的工作这里就不做了
    Api api = null;
    try {
      api = (Api) Class.forName(p.getProperty("ImplClass")).newInstance();
    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return api;
  }
}

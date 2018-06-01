package org.shiro.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午9:39:13
 * 类说明：Spring Context 工具类
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

  public static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext)
      throws BeansException {
    SpringContextUtils.applicationContext = applicationContext;
  }

  public static Object getBean(String name) {
    return applicationContext.getBean(name);
  }

  public static <T> T getBean(String name, Class<T> requiredType) {
    return applicationContext.getBean(name, requiredType);
  }

  public static boolean containsBean(String name) {
    return applicationContext.containsBean(name);
  }

  public static boolean isSingleton(String name) {
    return applicationContext.isSingleton(name);
  }

  public static Class<? extends Object> getType(String name) {
    return applicationContext.getType(name);
  }

}
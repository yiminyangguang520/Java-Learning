package com.mkyong.customer.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author bruce
 */
public class CustomerService implements InitializingBean, DisposableBean {

  public CustomerService() {
    System.out.println("执行CustomerService构造方法");
  }

  String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void afterPropertiesSet() throws Exception {
    System.out.println("Init method after properties are set : " + message);
  }

  public void destroy() throws Exception {
    System.out.println("Spring Container is destroy! Customer clean up");
  }

  @PostConstruct
  public void postConstructstroy() {
    System.out.println("注解-执行CustomerService：preDestroy方法");
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("注解--执行CustomerService：preDestroy方法");
  }

  public void initMethod() {
    System.out.println("XML配置-执行CustomerService：init-method方法");
  }

  public void destroyMethod() {
    System.out.println("XML配置-执行CustomerService：destroy-method方法");
  }
}

package com.memorynotfound.spring.core.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litz-a
 */
public class Run {

  public static void main(String... args) {
    ApplicationContext xmlContext = new ClassPathXmlApplicationContext("app-config.xml");
    SellPhone sellPhone = xmlContext.getBean(SellPhone.class);
    sellPhone.sendMessage();
  }
}

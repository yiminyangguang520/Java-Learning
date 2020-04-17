package com.memorynotfound.spring.core.lifecycle;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author min
 */
public class Run {

  public static void main(String... args) throws InterruptedException {
    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
    context.close();
  }
}

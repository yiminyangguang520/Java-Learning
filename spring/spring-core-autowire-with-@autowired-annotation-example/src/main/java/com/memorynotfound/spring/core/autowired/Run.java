package com.memorynotfound.spring.core.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litz-a
 */
public class Run {

  public static void main(String... args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
    Person person = context.getBean(Person.class);
    System.out.println(person);
  }
}

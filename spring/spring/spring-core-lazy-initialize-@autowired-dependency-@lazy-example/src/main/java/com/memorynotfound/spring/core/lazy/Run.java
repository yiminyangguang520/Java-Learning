package com.memorynotfound.spring.core.lazy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author min
 */
public class Run {

  public static void main(String... args) throws InterruptedException {
    ApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
    System.out.println("Application context loaded");
    System.out.println("Getting Person Bean");
    Person person = context.getBean(Person.class);

    System.out.println("Waiting...");
    Thread.sleep(1000);

    System.out.println("Getting the address");
    Address address = person.getAddress();
    System.out.println(address + ".toString()");
  }
}

package com.javacodegeeks.snippets.enterprise;

import com.javacodegeeks.snippets.enterprise.model.SimpleService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litz-a
 */
public class Application {

  public static void main(String[] args) {

    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    SimpleService simpleService = (SimpleService) context.getBean("simpleServiceBean");
    simpleService.printNameId();
    System.out.println("--------------");
    try {
      simpleService.checkName();
    } catch (Exception e) {
      System.out.println("SimpleService: Method checkName() exception thrown..");
    }
    System.out.println("--------------");
    simpleService.sayHello("Javacodegeeks");
    context.close();
  }
}

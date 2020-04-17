package com.javasampleapproach.beanpostprocessors;

import com.javasampleapproach.beanpostprocessors.service.CustomerService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author min
 */
@SpringBootApplication
public class SpringBeanPostProcessorsApplication {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

    System.out.println("Context has been initialized");
    CustomerService service = (CustomerService) context.getBean("customerService");
    System.out.println("Already retrieved Bean from context. Next, show Bean data.");
    System.out.println("Customer Name: " + service.getCustomer().getName());
    context.close();
  }
}

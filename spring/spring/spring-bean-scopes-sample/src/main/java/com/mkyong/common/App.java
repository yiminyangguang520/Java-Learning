package com.mkyong.common;

import com.mkyong.customer.services.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author min
 */
public class App {

  public static void main(String[] args) {
    ApplicationContext context =
        new ClassPathXmlApplicationContext(new String[]{"Spring-Customer.xml"});

    CustomerService custA = (CustomerService) context.getBean("customerService");
    custA.setMessage("Message by custA");
    System.out.println("Message : " + custA.getMessage());

    //retrieve it again
    CustomerService custB = (CustomerService) context.getBean("customerService");
    System.out.println("Message : " + custB.getMessage());
  }
}

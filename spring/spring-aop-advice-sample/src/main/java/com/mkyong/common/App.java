package com.mkyong.common;

import com.mkyong.customer.services.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litz-a
 */
public class App {

  public static void main(String[] args) {
    ApplicationContext appContext = new ClassPathXmlApplicationContext(
        new String[]{"Spring-Customer.xml"});

    CustomerService cust = (CustomerService) appContext.getBean("customerServiceProxy");

    System.out.println("*************************");
    cust.printName();
    System.out.println("*************************");
    cust.printURL();
    System.out.println("*************************");
    try {
      cust.printThrowException();
    } catch (Exception e) {

    }

  }
}
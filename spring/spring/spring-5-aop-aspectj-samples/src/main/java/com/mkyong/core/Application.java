package com.mkyong.core;

import com.mkyong.customer.bo.CustomerBo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litz-a
 */
public class Application {

  public static void main(String[] args) throws Exception {

    ApplicationContext appContext = new ClassPathXmlApplicationContext("Spring-Customer.xml");

    CustomerBo customer = (CustomerBo) appContext.getBean("customerBo");
    //customer.addCustomer();

    //customer.addCustomerReturnValue();

    //customer.addCustomerThrowException();

    customer.addCustomerAround("mkyong");

  }
}
package com.javasampleapproach.beanlifecycle;

import com.javasampleapproach.beanlifecycle.service.CustomerServiceImpAwareInterface;
import com.javasampleapproach.beanlifecycle.service.CustomerServiceImpCustomMethod;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author min
 */
@SpringBootApplication
public class SpringBeanLifeCycleApplication {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean_imp_custom_method.xml");

    System.out.println("Context has been initialized");
    CustomerServiceImpCustomMethod service = (CustomerServiceImpCustomMethod) context.getBean("customerServiceCustomerMethod");
    System.out.println("Already retrieved Bean from context. Next, show Bean data.");
    System.out.println("Customer Name: " + service.getCustomer().getName());
    context.close();

    System.out.println("-----new testing Context-----");
    System.out.println("-----Spring Aware Interface-----");

    context = new ClassPathXmlApplicationContext("bean_imp_aware.xml");
    context.getBean("customerServiceAware", CustomerServiceImpAwareInterface.class);
    context.close();
  }
}

package com.javasampleapproach.beanlifecycle.service;

import com.javasampleapproach.beanlifecycle.bean.Customer;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author min
 */
public class CustomerServiceImpCustomMethod implements InitializingBean, DisposableBean {

  private Customer customer;

  public CustomerServiceImpCustomMethod() {
    System.out.println("Call constructor for CustomerServiceImpCustomMethod");
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("afterPropertiesSet(): Bean initialization here");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("destroy(): Bean destruction here");
  }

  public void customInitBean() throws Exception {
    System.out.println("customInitBean()");
  }

  public void customDestroyBean() throws Exception {
    System.out.println("customDestroyBean()");
  }

  @PostConstruct
  public void postConstruct() {
    System.out.println(
        "post-construct():  perform some initialization after all the setter methods have been called");
  }

  @PreDestroy
  public void predestroy() {
    System.out.println("pre-destroy()");
  }
}

package com.javasampleapproach.beaninheritance.service;

/**
 * @author litz-a
 */
public class CustomerService {

  private String welcomeMessage;
  private String customerName;

  public String getWelcomeMessage() {
    return welcomeMessage;
  }

  public void setWelcomeMessage(String welcomeMessage) {
    this.welcomeMessage = welcomeMessage;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String sayHello() {
    return welcomeMessage + " " + customerName;
  }
}

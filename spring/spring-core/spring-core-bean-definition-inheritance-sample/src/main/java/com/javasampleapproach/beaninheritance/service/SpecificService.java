package com.javasampleapproach.beaninheritance.service;

/**
 * @author min
 */
public class SpecificService {

  private String welcomeMessage;
  private String customerName;
  private String serviceName;

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

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String sayHello() {
    return welcomeMessage + " " + customerName + ". This is " + serviceName;
  }

  public String sayWelcome() {
    return welcomeMessage + serviceName;
  }
}

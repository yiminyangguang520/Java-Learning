package com.javasampleapproach.scopeannotation.bean;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author min
 */
@RequestScope
@Component
public class Address {

  private String address = "US";

  public Address() {
    System.out.println("Create new Address: " + this.address);
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}

package com.javasampleapproach.scopeannotation.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
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

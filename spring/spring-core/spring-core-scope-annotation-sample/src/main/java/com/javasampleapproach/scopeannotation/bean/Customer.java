package com.javasampleapproach.scopeannotation.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class Customer {

  private String name;

  @Autowired
  private Age age;

  @Autowired
  private Address address;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age.getAge();
  }

  public void setAge(String age) {
    this.age.setAge(age);
  }

  public String getAddress() {
    return address.getAddress();
  }

  public void setAddress(String address) {
    this.address.setAddress(address);
  }
}

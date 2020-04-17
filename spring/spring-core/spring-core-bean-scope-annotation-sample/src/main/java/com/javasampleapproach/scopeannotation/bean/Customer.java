package com.javasampleapproach.scopeannotation.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class Customer {

  @Autowired
  private Name name;

  @Autowired
  private Age age;

  @Autowired
  private Address address;

  @Autowired
  private Language language;

  public String getCustomerName() {
    return name.toString();
  }

  public void setCustomerName(String firstName, String lastName) {
    this.name.setFirstName(firstName);
    this.name.setLastName(lastName);
  }

  public String getCustomerAge() {
    return age.getAge();
  }

  public void setCustomerAge(String age) {
    this.age.setAge(age);
  }

  public String getCustomerAddress() {
    return address.getAddress();
  }

  public void setCustomerAddress(String address) {
    this.address.setAddress(address);
  }

  public String getCustomerLanguage() {
    return language.getLanguage();
  }

  public String setCustomerLanguage(String language) {
    return this.language.setLanguage(language);
  }
}

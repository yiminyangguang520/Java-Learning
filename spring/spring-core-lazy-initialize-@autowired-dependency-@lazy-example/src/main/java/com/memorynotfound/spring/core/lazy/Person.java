package com.memorynotfound.spring.core.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class Person {

  @Autowired
  @Lazy
  private Address address;

  public Person() {
    System.out.println("Person initialized");
  }

  public Address getAddress() {
    return address;
  }
}

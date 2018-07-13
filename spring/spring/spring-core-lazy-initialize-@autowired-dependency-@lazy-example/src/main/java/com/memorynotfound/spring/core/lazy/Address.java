package com.memorynotfound.spring.core.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Lazy
@Component
public class Address {

  public Address() {
    System.out.println("Address initialized");
  }

  @Override
  public String toString() {
    return "Address{}";
  }
}

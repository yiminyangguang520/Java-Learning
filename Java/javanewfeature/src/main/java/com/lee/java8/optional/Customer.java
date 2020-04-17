package com.lee.java8.optional;

import java.util.Optional;

/**
 * @author min
 */
public class Customer {
  // private String name;
  // private String address;
  //
  // public Customer(String name) {
  // this.name = name;
  // }
  //
  // public String getName() {
  // return name;
  // }
  //
  // public void setName(String name) {
  // this.name = name;
  // }
  //
  // public String getAddress() {
  // return address;
  // }
  //
  // public void setAddress(String address) {
  // this.address = address;
  // }

  private String name;
  private Optional<Address> address = Optional.empty();

  public Customer(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Optional<Address> getAddress() {
    return address;
  }

  public void setAddress(Optional<Address> address) {
    this.address = address;
  }
}

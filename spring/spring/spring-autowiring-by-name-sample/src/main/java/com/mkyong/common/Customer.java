package com.mkyong.common;

/**
 * @author min
 */
public class Customer {

  private Address address;

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Customer [address=" + address + "]";
  }

}
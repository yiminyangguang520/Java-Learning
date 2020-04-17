package com.mkyong.customer.bo.impl;

import com.mkyong.customer.bo.CustomerBo;

/**
 * @author min
 */
public class CustomerBoImpl implements CustomerBo {

  public void addCustomer() {
    System.out.println("addCustomer() is running ");
  }

  public void addCustomerAfter() {
    System.out.println("addCustomerAfter() is running ");
  }

  public String addCustomerReturnValue() {
    System.out.println("addCustomerReturnValue() is running ");
    return "abc";
  }

  public void addCustomerThrowException() throws Exception {
    System.out.println("addCustomerThrowException() is running ");
    throw new Exception("Generic Error");
  }

  public void addCustomerAround(String name) {
    System.out.println("addCustomerAround() is running, args : " + name);
  }
}
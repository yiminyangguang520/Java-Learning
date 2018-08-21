package com.javasampleapproach.springioc.bean;

/**
 * @author litz-a
 */
public class BusinessPackage implements CustomerPackage {

  @Override
  public String support() {
    return "Support Business Customers";
  }
}

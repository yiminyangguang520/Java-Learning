package com.javasampleapproach.springioc.bean;

/**
 * @author min
 */
public class BusinessPackage implements CustomerPackage {

  @Override
  public String support() {
    return "Support Business Customers";
  }
}

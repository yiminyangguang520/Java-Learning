package com.javasampleapproach.springioc.bean;

/**
 * @author min
 */
public class BasicPackage implements CustomerPackage {

  @Override
  public String support() {
    return "Support Basic Customers";
  }
}

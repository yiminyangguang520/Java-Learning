package com.javasampleapproach.springioc.bean;

/**
 * @author litz-a
 */
public class BasicPackage implements CustomerPackage {

  @Override
  public String support() {
    return "Support Basic Customers";
  }
}

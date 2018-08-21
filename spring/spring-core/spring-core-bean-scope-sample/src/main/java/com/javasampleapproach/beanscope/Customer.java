package com.javasampleapproach.beanscope;

/**
 * @author litz-a
 */
public class Customer {

  private Name name;

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Customer: " + this.getName().getFirstName() + " "
        + this.getName().getLastName();
  }
}

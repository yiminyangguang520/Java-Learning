package com.javasampleapproach.injectcollections.model;

/**
 * @author min
 */
public class Customer {

  private long id;
  private String firstName;
  private String lastName;

  public Customer() {
    System.out.println("Customer()");
  }

  public Customer(long id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    System.out.println("Customer(long id, String firstName, String lastName)");
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}

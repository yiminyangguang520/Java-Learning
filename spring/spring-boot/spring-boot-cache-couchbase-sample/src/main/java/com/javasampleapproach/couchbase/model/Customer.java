package com.javasampleapproach.couchbase.model;

import java.io.Serializable;

/**
 * @author min
 */
public class Customer implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;

  private String firstName;

  private String lastName;

  public Customer(String id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return String.format("Customer[ id=%s, firstName=%s, lastName=%s]", this.id, this.firstName, this.lastName);
  }
}

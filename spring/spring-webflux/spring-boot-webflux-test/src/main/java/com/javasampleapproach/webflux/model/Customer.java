package com.javasampleapproach.webflux.model;

public class Customer {

  private long custId;
  private String firstname;
  private String lastname;
  private int age;

  public Customer() {
  }

  public Customer(long custId, String firstname, String lastname, int age) {
    this.custId = custId;
    this.firstname = firstname;
    this.lastname = lastname;
    this.age = age;
  }

  public long getCustId() {
    return custId;
  }

  public void setCustId(Long custId) {
    this.custId = custId;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Customer c = (Customer) o;

    if (custId != c.custId) {
      return false;
    }
    return firstname.equals(c.firstname);
  }

  @Override
  public int hashCode() {
    int result = Long.toString(custId).hashCode();
    result = 31 * result + firstname.hashCode();
    return result;
  }

  @Override
  public String toString() {
    String info = String.format("custId = %d, firstname = %s, lastname = %s, age = %d", custId, firstname, lastname, age);
    return info;
  }
}
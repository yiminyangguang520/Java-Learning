package com.spring.pojo;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author litz-a
 */
public class Employee {

  private String name;
  private String designation;
  private String company;

  public String getName() {
    return name;
  }

  @Required
  public void setName(String name) {
    this.name = name;
  }

  public String getDesignation() {
    return designation;
  }

  @Required
  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  @Override
  public String toString() {
    return "Employee [name=" + name + ", designation=" + designation + ", company=" + company + "]";
  }
}
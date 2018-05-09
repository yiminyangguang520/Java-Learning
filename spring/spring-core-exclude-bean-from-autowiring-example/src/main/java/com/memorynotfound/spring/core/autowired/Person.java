package com.memorynotfound.spring.core.autowired;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author litz-a
 */
public class Person {

  private String name;
  private Job job;

  public void setName(String name) {
    this.name = name;
  }

  @Autowired
  public void setJob(Job job) {
    this.job = job;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", job=" + job +
        '}';
  }
}

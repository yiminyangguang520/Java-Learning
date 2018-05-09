package com.memorynotfound.spring.core.autowired;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author litz-a
 */
public class Person {

  private String name;

  @Autowired
  private Job job;

  @Autowired
  public Person(Job job) {
    this.job = job;
  }

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

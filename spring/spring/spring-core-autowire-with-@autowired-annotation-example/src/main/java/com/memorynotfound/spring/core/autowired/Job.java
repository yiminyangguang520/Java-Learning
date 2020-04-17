package com.memorynotfound.spring.core.autowired;

/**
 * @author min
 */
public class Job {

  private String name;

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Job{" +
        "name='" + name + '\'' +
        '}';
  }
}

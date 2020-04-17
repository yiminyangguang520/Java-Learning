package com.memorynotfound.spring.core.lifecycle;

/**
 * @author min
 */
public class Course {

  private String name;

  public void init() {
    System.out.println("- - - initializing course bean using default-init-method");
    System.out.println("name: " + name);
  }

  public void destroy() {
    System.out.println("- - - destroying course bean using default-destroy-method");
  }

  public void setName(String name) {
    this.name = name;
  }
}

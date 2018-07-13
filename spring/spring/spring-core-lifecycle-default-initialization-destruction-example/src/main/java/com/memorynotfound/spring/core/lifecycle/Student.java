package com.memorynotfound.spring.core.lifecycle;

/**
 * @author litz-a
 */
public class Student {

  public void init() {
    System.out.println("- - - initializing student bean using default-init-method");
    System.out.println("name: " + name);
  }

  private String name;

  public void setName(String name) {
    this.name = name;
  }
}

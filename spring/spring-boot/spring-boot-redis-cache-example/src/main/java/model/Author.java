package com.example.model;

import java.io.Serializable;

/**
 * @author litz-a
 */
public class Author implements Serializable {

  private String name;

  public Author() {
  }

  public Author(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

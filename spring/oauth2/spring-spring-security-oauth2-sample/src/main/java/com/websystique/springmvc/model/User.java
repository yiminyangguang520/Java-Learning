package com.websystique.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author min
 */
@Data
@AllArgsConstructor
public class User {

  private long id;

  private String name;

  private int age;

  private double salary;

  public User() {
    id = 0;
  }
}

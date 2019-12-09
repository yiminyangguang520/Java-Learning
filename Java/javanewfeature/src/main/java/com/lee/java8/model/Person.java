package com.lee.java8.model;

import java.util.Optional;

/**
 * @author litz-a
 */
public class Person {

  private Optional<Car> car;

  public Optional<Car> getCar() {
    return this.car;
  }
}

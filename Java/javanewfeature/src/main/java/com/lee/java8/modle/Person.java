package com.lee.java8.modle;

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

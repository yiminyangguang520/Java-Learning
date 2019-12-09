package com.lee.java8.model;

import java.util.Optional;

/**
 * @author litz-a
 */
public class Car {

  private Optional<Insurance> insurance;

  public Optional<Insurance> getInsurance() {
    return insurance;
  }
}

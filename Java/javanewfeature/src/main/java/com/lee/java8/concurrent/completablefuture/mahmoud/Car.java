package com.lee.java8.concurrent.completablefuture.mahmoud;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author min
 */
@Setter
@Getter
@ToString
public class Car {

  private int id;
  private int manufacturerId;
  private String model;
  private int year;
  private float rating;

  public Car(int id, int manufacturerId, String model, int year) {
    this.id = id;
    this.manufacturerId = manufacturerId;
    this.model = model;
    this.year = year;
  }
}
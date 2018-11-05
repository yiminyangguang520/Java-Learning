package com.technicalkeeda.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author litz-a
 */
@Document(collection = "cars")
public class Car {

  @Id
  private String id;

  private String brand;

  private String model;

  private int numberOfCars;

  private int salesyear;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getNumberOfCars() {
    return numberOfCars;
  }

  public void setNumberOfCars(int numberOfCars) {
    this.numberOfCars = numberOfCars;
  }

  public int getSalesyear() {
    return salesyear;
  }

  public void setSalesyear(int salesyear) {
    this.salesyear = salesyear;
  }


}
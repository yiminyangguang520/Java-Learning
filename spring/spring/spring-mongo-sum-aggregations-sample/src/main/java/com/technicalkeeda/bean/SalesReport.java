package com.technicalkeeda.bean;

/**
 * @author min
 */
public class SalesReport {

  private String brand;

  private long total;

  private int salesyear;

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public int getSalesyear() {
    return salesyear;
  }

  public void setSalesyear(int salesyear) {
    this.salesyear = salesyear;
  }


  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("Brand :- " + getBrand()).append(", Sales Year :- " + getSalesyear()).append(", Total Car Sold:- " + getTotal());
    return s.toString();
  }

}
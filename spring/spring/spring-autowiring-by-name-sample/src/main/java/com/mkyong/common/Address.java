package com.mkyong.common;

/**
 * @author min
 */
public class Address {

  private String fulladdress;

  public String getFulladdress() {
    return fulladdress;
  }

  public void setFulladdress(String fulladdress) {
    this.fulladdress = fulladdress;
  }

  @Override
  public String toString() {
    return "Address [fulladdress=" + fulladdress + "]";
  }

}
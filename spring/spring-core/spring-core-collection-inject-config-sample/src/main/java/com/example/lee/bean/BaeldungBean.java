package com.example.lee.bean;

/**
 *
 * @author Gebruiker
 * @date 5/22/2018
 */
public class BaeldungBean {

  private String name;

  public BaeldungBean(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}

package com.lee.java8.maplistconverter;

/**
 * @author litz-a
 */
public class Model {

  private Integer id;
  private String data;

  public Model(Integer id, String data) {
    this.id = id;
    this.data = data;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  @Override
  public String toString() {
    String info = String.format("Model info: id = %d, data = %s", id, data);
    return info;
  }
}
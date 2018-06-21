package com.glodon.es.config;

/**
 * @Author sunyd 【sunyd@glodon.com】
 * @Date 2017/9/12 15:38
 */
public class Entity {

  private String key;
  private String value;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Entity(String key, String value) {
    this.key = key;
    this.value = value;
  }
}

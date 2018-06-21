package com.example.entity;

/**
 * @author litz-a
 */
public class Teacher {

  private Integer id;

  private String name;

  private String sex;

  private String major;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex == null ? null : sex.trim();
  }

  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major == null ? null : major.trim();
  }
}
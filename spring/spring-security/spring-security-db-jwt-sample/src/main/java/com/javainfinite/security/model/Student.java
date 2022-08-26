package com.javainfinite.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(name = "username")
  private String sname;

  @Column(name = "password")
  private String password;

  @Column(name = "roles")
  private String srole;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSname() {
    return sname;
  }

  public void setSname(String sname) {
    this.sname = sname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSrole() {
    return srole;
  }

  public void setSrole(String srole) {
    this.srole = srole;
  }
}

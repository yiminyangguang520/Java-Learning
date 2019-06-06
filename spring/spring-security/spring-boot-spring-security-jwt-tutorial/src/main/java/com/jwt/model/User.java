package com.jwt.model;

import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class User {

  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
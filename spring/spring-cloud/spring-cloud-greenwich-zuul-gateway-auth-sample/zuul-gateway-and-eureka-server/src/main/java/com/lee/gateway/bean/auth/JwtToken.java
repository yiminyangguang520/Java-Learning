package com.lee.gateway.bean.auth;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author min
 */
@Document
public class JwtToken {

  @Id
  private String token;

  public JwtToken(String token) {
    this.token = token;
  }

  public JwtToken() {
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}

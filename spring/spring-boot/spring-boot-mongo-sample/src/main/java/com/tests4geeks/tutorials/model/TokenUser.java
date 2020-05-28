package com.tests4geeks.tutorials.model;

public class TokenUser extends AbstractBaseEntity {

  private String token;


  public String getToken() {
    return token;
  }

  public TokenUser setToken(String token) {
    this.token = token;
    return this;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("TokenUser{");
    sb.append("token='").append(token).append('\'');
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
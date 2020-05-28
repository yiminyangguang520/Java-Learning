package com.tests4geeks.tutorials.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * {
 * 	"uId": "111",
 * 	"userName": "bruce",
 * 	"password": "test",
 * 	"roleName": "USER",
 * 	"type": "role"
 * }
 * @author min
 */
@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = RoleUser.class, name = "role"), @JsonSubTypes.Type(value = TokenUser.class, name = "token")})
public abstract class AbstractBaseEntity {

  private String uId;
  private String userName;
  private String password;

  public String getuId() {
    return uId;
  }

  public void setuId(String uId) {
    this.uId = uId;
  }

  public String getUserName() {
    return userName;
  }

  public AbstractBaseEntity setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public AbstractBaseEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("AbstractBaseEntity{");
    sb.append("userName='").append(userName).append('\'');
    sb.append(", password='").append(password).append('\'');
    sb.append('}');
    return sb.toString();
  }
}

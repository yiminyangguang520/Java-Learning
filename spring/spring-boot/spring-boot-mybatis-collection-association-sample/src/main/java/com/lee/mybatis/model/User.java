package com.lee.mybatis.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class User {

  private Integer id;

  private String username;

  private String password;

  private Integer enabled;

  private Integer expired;

  private Integer locked;

  private Date lastUpdateTime;

  private String comment;

  @Getter
  @Setter
  private Role role;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username == null ? null : username.trim();
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password == null ? null : password.trim();
  }

  public Integer getEnabled() {
    return enabled;
  }

  public void setEnabled(Integer enabled) {
    this.enabled = enabled;
  }

  public Integer getExpired() {
    return expired;
  }

  public void setExpired(Integer expired) {
    this.expired = expired;
  }

  public Integer getLocked() {
    return locked;
  }

  public void setLocked(Integer locked) {
    this.locked = locked;
  }

  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment == null ? null : comment.trim();
  }
}
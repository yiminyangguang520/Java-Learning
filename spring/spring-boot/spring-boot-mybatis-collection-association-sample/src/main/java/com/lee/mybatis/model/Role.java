package com.lee.mybatis.model;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Role {

  private Integer id;

  private String name;

  private String description;

  private Integer isActive;

  private Date lastUpdateTime;

  @Getter
  @Setter
  private List<Menu> menus;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description == null ? null : description.trim();
  }

  public Integer getIsActive() {
    return isActive;
  }

  public void setIsActive(Integer isActive) {
    this.isActive = isActive;
  }

  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }
}
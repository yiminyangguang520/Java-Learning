package com.tests4geeks.tutorials.model;

public class RoleUser extends AbstractBaseEntity {

  private String roleName;


  public String getRoleName() {
    return roleName;
  }

  public RoleUser setRoleName(String roleName) {
    this.roleName = roleName;
    return this;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("RoleUser{");
    sb.append("roleName='").append(roleName).append('\'');
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
package com.lee.authlogin.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author min
 */
@Entity
@Table(name = "sys_role")
public class UserRole {

  private String roleCode;

  private String parentRoleCode;

  private String roleDescription;

  private List<User> users;

  @Id
  @Column(name = "role_code")
  public String getRoleCode() {
    return roleCode;
  }

  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
  }

  @Column(name = "parent_role_code")
  public String getParentRoleCode() {
    return parentRoleCode;
  }

  public void setParentRoleCode(String parentRoleCode) {
    this.parentRoleCode = parentRoleCode;
  }

  @Column(name = "role_desc")
  public String getRoleDescription() {
    return roleDescription;
  }

  public void setRoleDescription(String roleDescription) {
    this.roleDescription = roleDescription;
  }

  @ManyToMany(mappedBy = "roles")
  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}

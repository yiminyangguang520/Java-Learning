package com.lee.authlogin.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author litz-a
 */
@Entity
@Table(name = "sys_user")
public class User {

  private String userName;
  private String userDescription;
  private String password;
  private List<UserRole> roles;

  @Id
  @Column(name = "user_name")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Column(name = "user_desc")
  public String getUserDescription() {
    return userDescription;
  }

  public void setUserDescription(String userDescription) {
    this.userDescription = userDescription;
  }

  @Column(name = "password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @ManyToMany
  @JoinTable(name = "sys_user_role",
      joinColumns = @JoinColumn(name = "user_name", referencedColumnName = "user_name", updatable = false, insertable = false),
      inverseJoinColumns = @JoinColumn(name = "role_code", referencedColumnName = "role_code", updatable = false, insertable = false))
  public List<UserRole> getRoles() {
    return roles;
  }

  public void setRoles(List<UserRole> roles) {
    this.roles = roles;
  }
}

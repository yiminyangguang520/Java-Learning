package com.us.example.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author litz-a
 */
public class User {

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column sys_user.id
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  private Integer id;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column sys_user.username
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  private String username;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column sys_user.password
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  private String password;


  @Getter
  @Setter
  private List<Role> roles;

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column sys_user.id
   *
   * @return the value of sys_user.id
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public Integer getId() {
    return id;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column sys_user.id
   *
   * @param id the value for sys_user.id
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column sys_user.username
   *
   * @return the value of sys_user.username
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public String getUsername() {
    return username;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column sys_user.username
   *
   * @param username the value for sys_user.username
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public void setUsername(String username) {
    this.username = username == null ? null : username.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column sys_user.password
   *
   * @return the value of sys_user.password
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public String getPassword() {
    return password;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column sys_user.password
   *
   * @param password the value for sys_user.password
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public void setPassword(String password) {
    this.password = password == null ? null : password.trim();
  }
}
package com.xiaoze.api.entity;


import java.io.Serializable;
import org.springframework.stereotype.Component;

/**
 * User
 *
 * @author xiaoze
 * @date 2018/6/3
 */
@Component
public class User implements Serializable {

  private String userNo;

  private String userName;

  private String userPwd;

  public String getUserNo() {
    return userNo;
  }

  public void setUserNo(String userNo) {
    this.userNo = userNo;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPwd() {
    return userPwd;
  }

  public void setUserPwd(String userPwd) {
    this.userPwd = userPwd;
  }

}

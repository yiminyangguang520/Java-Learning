package com.scienjus.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户数据的domain类
 *
 * @author ScienJus
 * @date 2015/7/31.
 */
@Getter
@Setter
@Entity
@Table(name = "user_")
public class User {

  /**
   * 用户名
   */
  @Column(name = "username_")
  private String username;

  /**
   * 密码
   */
  @Column(name = "password_")
  private String password;

  /**
   * 用户id
   */
  @Id
  @Column(name = "id_")
  private long id;

  /**
   * 昵称
   */
  @Column(name = "nickname_")
  private String nickname;
}

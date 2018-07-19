package com.glodon.sso.server.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author litz-a
 */
@Setter
@Getter
public class UserInfo {

  private int id;
  private String username;
  private String password;
}

package com.us.example.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yangyibo
 * @date 17/1/17
 */
@Getter
@Setter
public class SysUser {

  private Integer id;
  private String username;
  private String password;

  private List<SysRole> roles;
}

package com.lee.sso.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 大部分时候直接用User即可不必扩展
 *
 * @author ChengJianSheng
 * @date 2019-02-11
 */
@Data
public class CustomUser extends User {

  /**
   * 举个例子，部门ID
   */
  private Integer departmentId;

  /**
   * 举个例子，假设我们想增加一个字段，这里我们增加一个mobile表示手机号
   */
  private String mobile;

  public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }

  public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
  }
}

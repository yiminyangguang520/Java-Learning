package com.example.webfluxsessiondemo.security.userdetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author wangxing
 * @create 2018/10/15
 */
public class UserInfo<T> extends User implements UserIdUserDetails<String> {

  private static final long serialVersionUID = -2708917853989296238L;

  private String userId;

  private T user;

  public UserInfo(final T user, final String userId, final String username, final String password, final Collection<? extends GrantedAuthority> authorities) {
    this(user, userId, username, password, true, true, true, true, authorities);
  }

  public UserInfo(final T user, final String userId, final String username, final String password, final boolean enabled, final boolean accountNonExpired,
      final boolean credentialsNonExpired, final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    this.userId = userId;
    this.user = user;
  }

  @JsonIgnore
  public T getUser() {
    return this.user;
  }

  public void setUser(final T user) {
    this.user = user;
  }

  @Override
  public String getUserId() {
    return this.userId;
  }

  @Override
  @JsonIgnore
  public String getPassword() {
    return super.getPassword();
  }
}

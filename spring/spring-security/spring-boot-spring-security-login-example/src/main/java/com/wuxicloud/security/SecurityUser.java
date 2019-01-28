package com.wuxicloud.security;

import com.wuxicloud.model.User;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser extends User implements UserDetails {

  private static final long serialVersionUID = 1L;

  public SecurityUser(User user) {
    if (user != null) {
      this.setUser_uuid(user.getUser_uuid());
      this.setUsername(user.getUsername());
      this.setPassword(user.getPassword());
      this.setEmail(user.getEmail());
      this.setTelephone(user.getTelephone());
      this.setRole(user.getRole());
      this.setImage(user.getImage());
      this.setLast_ip(user.getLast_ip());
      this.setLast_time(user.getLast_time());
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    String username = this.getUsername();
    if (username != null) {
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority(username);
      authorities.add(authority);
    }
    return authorities;
  }

  //账户是否未过期,过期无法验证
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  //指定用户是否解锁,锁定的用户无法进行身份验证
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  //是否可用 ,禁用的用户不能身份验证
  @Override
  public boolean isEnabled() {
    return true;
  }
}

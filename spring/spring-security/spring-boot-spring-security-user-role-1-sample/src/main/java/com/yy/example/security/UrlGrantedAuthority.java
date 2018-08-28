package com.yy.example.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author litz-a
 * @date 17/2/15
 */
@Getter
@Setter
@AllArgsConstructor
public class UrlGrantedAuthority implements GrantedAuthority {

  private String permissionUrl;
  private String method;

  @Override
  public String getAuthority() {
    return this.permissionUrl + ";" + this.method;
  }
}

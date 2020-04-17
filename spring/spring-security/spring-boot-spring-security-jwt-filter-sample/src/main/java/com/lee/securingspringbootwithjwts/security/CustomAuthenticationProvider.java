package com.lee.securingspringbootwithjwts.security;

import com.lee.securingspringbootwithjwts.model.GrantedAuthorityImpl;
import java.util.ArrayList;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义身份认证验证组件
 * @author min
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    // 获取认证的用户名 & 密码
    String name = authentication.getName();
    String password = authentication.getCredentials().toString();

    // 认证逻辑
    if (name.equals("admin") && password.equals("123456")) {

      // 这里设置权限和角色
      ArrayList<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
      authorities.add(new GrantedAuthorityImpl("AUTH_WRITE"));
      // 生成令牌
      Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
      return auth;
    } else {
      throw new BadCredentialsException("密码错误~");
    }
  }

  /**
   * 是否可以提供输入类型的认证服务
   * @param authentication
   * @return
   */
  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

}

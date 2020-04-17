package com.lee.security;

import java.util.Collection;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 访问决策管理器
 * 决策某个资源（页面）是否可被当前用户所属的角色访问
 * @author min
 */
public class AccessDecisionManagerImpl implements AccessDecisionManager {

  @Override
  public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
      throws AccessDeniedException, InsufficientAuthenticationException {
    if (configAttributes == null) {
      return;
    }

    for (ConfigAttribute ca : configAttributes) {
      String needRole = ca.getAttribute();
      for (GrantedAuthority ga : authentication.getAuthorities()) {
        if (needRole.equals(ga.getAuthority())) {
          return;
        }
      }
    }

    throw new AccessDeniedException("no right");
  }

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return true;
  }
}

package com.us.example.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *
 * @author yangyibo
 * @date 17/1/19
 */
@Service
public class CustomAccessDecisionManager implements AccessDecisionManager {

  /**
   * decide 方法是判定是否拥有权限的决策方法，
   * authentication 是释CustomUserService中循环添加到 GrantedAuthority 对象中的权限信息集合.
   * object 包含客户端发起的请求的requset信息，可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
   * configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
   * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行
   * @param authentication
   * @param object
   * @param configAttributes
   * @throws AccessDeniedException
   * @throws InsufficientAuthenticationException
   */
  @Override
  public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
      throws AccessDeniedException, InsufficientAuthenticationException {
    if (null == configAttributes || configAttributes.size() <= 0) {
      return;
    }

    List<String> needRoleList = configAttributes.stream()
        .map(configAttribute -> configAttribute.getAttribute().trim())
        .collect(Collectors.toList());

    List<String> authorityList = authentication.getAuthorities().stream()
        .map(grantedAuthority -> grantedAuthority.getAuthority())
        .collect(Collectors.toList());

    // 判断两个集合中是否有想等的元素
    boolean result = needRoleList.stream()
        .anyMatch(item -> authorityList.stream().anyMatch(item::equals));

    if (!result) {
      throw new AccessDeniedException("no right");
    }
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

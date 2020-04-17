package com.yy.example.security;

import java.util.Collection;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

/**
 * @author min
 * @date 17/1/19
 */
@Service
public class UrlMetadataSourceService implements FilterInvocationSecurityMetadataSource {

  @Override
  public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
    final HttpServletRequest request = ((FilterInvocation) object).getRequest();
    return Collections.singletonList(new UrlConfigAttribute(request));
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return null;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return true;
  }
}

package com.yy.example.security;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.ConfigAttribute;

/**
 * @author litz-a
 * @date 17/2/15
 */
public class UrlConfigAttribute implements ConfigAttribute {

  private final HttpServletRequest httpServletRequest;

  public UrlConfigAttribute(HttpServletRequest httpServletRequest) {
    this.httpServletRequest = httpServletRequest;
  }


  @Override
  public String getAttribute() {
    return null;
  }

  public HttpServletRequest getHttpServletRequest() {
    return httpServletRequest;
  }
}
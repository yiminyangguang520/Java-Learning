package com.packtpub.springsecurity.web.access.expression;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class CustomWebExpression {

  public boolean isLocalHost(final Authentication authentication,
      final HttpServletRequest request) {
//        System.out.println("Server name" + request.getServerName());
//        return "localhost".equals(request.getServerName());
    return true;
  }

}
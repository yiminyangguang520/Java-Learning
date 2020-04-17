package com.javadeveloperzone.handler;

import java.io.IOException;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author min
 * Created by JavaDeveloperZone on 13-11-2017. Spring Security will send control to AuthenticationSuccessHandler when authentication will get success
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws
      IOException, ServletException {
    User principal = (User) authentication.getPrincipal();
    System.out.println("principal" + principal.getUsername());
    boolean isAdmin = false;
    Iterator<GrantedAuthority> grantedAuthorityIterator = principal.getAuthorities().iterator();
    while (grantedAuthorityIterator.hasNext()) {
      if ("ROLE_ADMIN".equalsIgnoreCase(grantedAuthorityIterator.next().getAuthority())) {
        isAdmin = true;
      }
    }
    if (isAdmin) {
      response.sendRedirect("/admin/home");
    } else {
      response.sendRedirect("/user/home");
    }
  }
}


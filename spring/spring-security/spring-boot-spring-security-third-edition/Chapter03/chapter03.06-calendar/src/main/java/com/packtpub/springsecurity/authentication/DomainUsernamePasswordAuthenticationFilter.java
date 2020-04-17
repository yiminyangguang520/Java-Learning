package com.packtpub.springsecurity.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author min
 */
public final class DomainUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    if (!request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException("Authentication method not supported:" + request.getMethod());
    }
    String username = obtainUsername(request);
    String password = obtainPassword(request);
    String domain = request.getParameter("domain");
    // authRequest.isAuthenticated() =false since no authorities are specified
    DomainUsernamePasswordAuthenticationToken authRequest = new DomainUsernamePasswordAuthenticationToken(username, password, domain);
    setDetails(request, authRequest);
    return this.getAuthenticationManager().authenticate(authRequest);
  }
}
package com.hellokoding.sso.resource;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author min
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

  private static final String jwtTokenCookieName = "JWT-TOKEN";
  private static final String signingKey = "signingKey";

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
      throws ServletException, IOException {
    String username = JwtUtil.parseToken(httpServletRequest, jwtTokenCookieName, signingKey);
    if (username == null) {
      String authService = this.getFilterConfig().getInitParameter("services.auth");
      httpServletResponse.sendRedirect(authService + "?redirect=" + httpServletRequest.getRequestURL());
    } else {
      httpServletRequest.setAttribute("username", username);
      filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
  }
}

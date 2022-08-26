package com.javainfinite.security.filter;

import com.javainfinite.security.util.JwtTokenValidator;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenValidationFilter extends OncePerRequestFilter {

  Logger logger = LoggerFactory.getLogger(JwtTokenValidationFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    logger.info("Request received for Jwt token validation");
    JwtTokenValidator validator = new JwtTokenValidator();
    validator.validateJwtToken(request, response, false);
    filterChain.doFilter(request, response);

  }
}

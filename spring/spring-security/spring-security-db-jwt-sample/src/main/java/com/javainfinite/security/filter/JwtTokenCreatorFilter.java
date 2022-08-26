package com.javainfinite.security.filter;

import com.javainfinite.security.util.JwtTokenCreator;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenCreatorFilter extends OncePerRequestFilter {

  Logger logger = LoggerFactory.getLogger(JwtTokenCreatorFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    logger.info("Token generator class");
    JwtTokenCreator creator = new JwtTokenCreator();
    creator.generateToken(request, response);
    filterChain.doFilter(request, response);
  }
}

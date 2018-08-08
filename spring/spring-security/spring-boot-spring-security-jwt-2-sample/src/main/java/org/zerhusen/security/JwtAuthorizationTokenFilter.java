package org.zerhusen.security;

import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author litz-a
 */
@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final String auth_token_start = "Bearer ";

  private final UserDetailsService userDetailsService;
  private final JwtTokenUtil jwtTokenUtil;
  private final String tokenHeader;

  public JwtAuthorizationTokenFilter(@Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService,
    JwtTokenUtil jwtTokenUtil,
    @Value("${jwt.header}") String tokenHeader) {
    this.userDetailsService = userDetailsService;
    this.jwtTokenUtil = jwtTokenUtil;
    this.tokenHeader = tokenHeader;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    logger.debug("processing authentication for '{}'", request.getRequestURL());

    final String requestHeader = request.getHeader(this.tokenHeader);

    String username = null;
    String authToken = null;
    if (!StringUtils.isEmpty(requestHeader) && requestHeader.startsWith(auth_token_start)) {
      authToken = requestHeader.substring(auth_token_start.length());
      try {
        username = jwtTokenUtil.getUsernameFromToken(authToken);
      } catch (IllegalArgumentException e) {
        logger.error("an error occured during getting username from token", e);
      } catch (ExpiredJwtException e) {
        logger.warn("the token is expired and not valid anymore", e);
      }
    } else {
      logger.warn("couldn't find bearer string, will ignore the header");
    }

    logger.debug("checking authentication for user '{}'", username);
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      logger.debug("security context was null, so authorizating user");

      // It is not compelling necessary to load the use details from the database. You could also store the information
      // in the token and read it from it. It's up to you ;)
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

      // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
      // the database compellingly. Again it's up to you ;)
      if (jwtTokenUtil.validateToken(authToken, userDetails)) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        logger.info("authorizated user '{}', setting security context", username);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    chain.doFilter(request, response);
  }
}

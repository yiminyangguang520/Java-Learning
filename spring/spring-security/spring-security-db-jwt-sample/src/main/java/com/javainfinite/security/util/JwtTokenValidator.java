package com.javainfinite.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtTokenValidator {

  Logger logger = LoggerFactory.getLogger(JwtTokenValidator.class);

  public void validateJwtToken(HttpServletRequest request, HttpServletResponse response, boolean isRefreshValidation) {

    String token = request.getHeader(SecurityContants.AUTHORIZATION_HEADER);
    String refresh = request.getHeader(SecurityContants.REFRESH_HEADER);

    logger.info("Authorization Token: {}", token);

    if (token != null && !token.contains("Basic")) {
      try {
        SecretKey key = Keys.hmacShaKeyFor(
            SecurityContants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(isRefreshValidation ? refresh : token)
            .getBody();

        String username = String.valueOf(claims.get("username"));
        String authorities = (String) claims.get("authorities");

        Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
            getStudentRoles(authorities));

        SecurityContextHolder.getContext().setAuthentication(auth);
      } catch (ExpiredJwtException ex) {
        logger.info("Token expired!");

      } catch (Exception e) {
        throw new BadCredentialsException("Invalid Token received!");
      }
    }

  }

  private List<GrantedAuthority> getStudentRoles(String authorities) {
    List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
    String[] roles = authorities.split(",");
    for (String role : roles) {
      grantedAuthorityList.add(new SimpleGrantedAuthority(role.replaceAll("\\s+", "")));
    }

    return grantedAuthorityList;
  }
}

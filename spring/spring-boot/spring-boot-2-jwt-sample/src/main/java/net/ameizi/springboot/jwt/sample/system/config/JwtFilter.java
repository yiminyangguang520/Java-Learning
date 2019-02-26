package net.ameizi.springboot.jwt.sample.system.config;

import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ameizi.springboot.jwt.sample.system.exception.AuthorizationException;
import net.ameizi.springboot.jwt.sample.system.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class JwtFilter extends HandlerInterceptorAdapter {

  @Autowired
  private JwtUtil jwtUtil;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      throw new AuthorizationException("认证失败");
    }

    // The part after "Bearer "
    String token = authHeader.substring(7);
    Claims claims = jwtUtil.validateToken(token);

    if (claims != null) {
      request.setAttribute("claims", claims);
      return true;
    } else {
      throw new AuthorizationException("认证失败");
    }
  }

}
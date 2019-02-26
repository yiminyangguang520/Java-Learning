package net.ameizi.springboot.jwt.sample.system.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class JwtTokenHelp {

  /**
   * 从request中获取token
   */
  public static String getToken(HttpServletRequest request) {
    // 先从Header里面获取
    String token = request.getHeader("token");
    if (StringUtils.isBlank(token)) {
      // 获取不到再从Parameter中拿 为了兼容目前的api-docs支持
      token = request.getParameter("token");
      // 还是获取不到再从Cookie中拿
      if (StringUtils.isBlank(token)) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
          for (Cookie cookie : cookies) {
            if (StringUtils.equalsIgnoreCase(cookie.getName(), "token")) {
              token = cookie.getValue();
            }
          }
        }
      }
    }
    return token;
  }

}

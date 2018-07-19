package com.glodon.sso.core.util;

import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
public class CookieUtil {

  /**
   * 默认缓存时间:2小时
   */
  private static final int COOKIE_MAX_AGE = 60 * 60 * 2;

  /**
   * 保存路径:根路径
   */
  private static final String COOKIE_PATH = "/";

  /**
   * 保存
   */
  public static void set(HttpServletResponse response, String key, String value, boolean ifRemember) {
    int age = ifRemember ? COOKIE_MAX_AGE : -1;
    set(response, key, value, null, COOKIE_PATH, age, true);
  }

  /**
   * 保存
   */
  private static void set(HttpServletResponse response, String key, String value, String domain, String path, int maxAge, boolean isHttpOnly) {
    Cookie cookie = new Cookie(key, value);
    if (domain != null) {
      cookie.setDomain(domain);
    }
    cookie.setPath(path);
    cookie.setMaxAge(maxAge);
    cookie.setHttpOnly(isHttpOnly);
    response.addCookie(cookie);
  }

  /**
   * 查询value
   */
  public static String getValue(HttpServletRequest request, String key) {
    Cookie cookie = get(request, key);
    if (cookie != null) {
      return cookie.getValue();
    }
    return null;
  }

  /**
   * 查询Cookie
   */
  private static Cookie get(HttpServletRequest request, String key) {
    Cookie[] arr_cookie = request.getCookies();
    if (arr_cookie != null && arr_cookie.length > 0) {
      return Arrays.stream(arr_cookie)
          .filter(cookie -> cookie.getName().equals(key))
          .findFirst()
          .orElse(null);
    }
    return null;
  }

  /**
   * 删除Cookie
   */
  public static void remove(HttpServletRequest request, HttpServletResponse response, String key) {
    Cookie cookie = get(request, key);
    if (cookie != null) {
      set(response, key, "", null, COOKIE_PATH, 0, true);
    }
  }

}
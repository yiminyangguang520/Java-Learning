package com.glodon.sso.core.util;

import com.glodon.sso.core.constant.Constant;
import com.glodon.sso.core.store.SsoLoginStore;
import com.glodon.sso.core.user.SsoUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * @author litz-a
 */
public class SsoLoginHelper {

  public static String getSessionIdByCookie(HttpServletRequest request) {
    return CookieUtil.getValue(request, Constant.SSO_SESSIONID);
  }

  public static void setSessionIdInCookie(HttpServletResponse response, String sessionId) {
    if (StringUtils.isNotEmpty(sessionId)) {
      CookieUtil.set(response, Constant.SSO_SESSIONID, sessionId, false);
    }
  }

  public static void removeSessionIdInCookie(HttpServletRequest request, HttpServletResponse response) {
    CookieUtil.remove(request, response, Constant.SSO_SESSIONID);
  }

  public static String cookieSessionIdGetByHeader(HttpServletRequest request) {
    String cookieSessionId = request.getHeader(Constant.SSO_SESSIONID);
    return cookieSessionId;
  }

  public static SsoUser loginCheck(HttpServletRequest request) {
    String cookieSessionId = getSessionIdByCookie(request);
    if (StringUtils.isNotEmpty(cookieSessionId)) {
      return loginCheck(cookieSessionId);
    }
    return null;
  }

  public static SsoUser loginCheck(String sessionId) {
    if (StringUtils.isNotEmpty(sessionId)) {
      SsoUser ssoUser = SsoLoginStore.get(sessionId);
      if (ssoUser != null) {
        return ssoUser;
      }
    }

    return null;
  }

  public static void login(HttpServletResponse response, String sessionId, SsoUser ssoUser) {
    SsoLoginStore.put(sessionId, ssoUser);
    CookieUtil.set(response, Constant.SSO_SESSIONID, sessionId, false);
  }

  public static void login(String sessionId, SsoUser ssoUser) {
    SsoLoginStore.put(sessionId, ssoUser);
  }

  public static void logout(HttpServletRequest request, HttpServletResponse response) {
    String cookieSessionId = getSessionIdByCookie(request);

    if (StringUtils.isNotEmpty(cookieSessionId)) {
      SsoLoginStore.remove(cookieSessionId);
    }

    CookieUtil.remove(request, response, Constant.SSO_SESSIONID);
  }


  public static void logout(String sessionId) {
    SsoLoginStore.remove(sessionId);
  }

}

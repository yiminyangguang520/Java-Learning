package com.glodon.sso.core.filter;

import com.glodon.sso.core.constant.Constant;
import com.glodon.sso.core.user.SsoUser;
import com.glodon.sso.core.util.JacksonUtil;
import com.glodon.sso.core.util.SsoLoginHelper;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * web sso filter
 *
 * @author litz-a
 */
public class GcrSsoFilter extends HttpServlet implements Filter {

  private static Logger logger = LoggerFactory.getLogger(GcrSsoFilter.class);

  private String ssoServer;
  private String logoutPath;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    ssoServer = filterConfig.getInitParameter(Constant.SSO_SERVER);
    if (StringUtils.isNotEmpty(ssoServer)) {
      logoutPath = filterConfig.getInitParameter(Constant.SSO_LOGOUT_PATH);
    }

    logger.info("GcrSsoFilter init.");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    String servletPath = ((HttpServletRequest) request).getServletPath();
    String link = req.getRequestURL().toString();

    // logout filter
    if (StringUtils.isNotEmpty(logoutPath) && logoutPath.equals(servletPath)) {
      // remove cookie
      SsoLoginHelper.removeSessionIdInCookie(req, res);
      // redirect logout
      String logoutPageUrl = ssoServer.concat(Constant.SSO_LOGOUT);
      res.sendRedirect(logoutPageUrl);
      return;
    }

    // login filter
    // valid cookie user
    String cookieSessionId = SsoLoginHelper.getSessionIdByCookie(req);
    SsoUser ssoUser = SsoLoginHelper.loginCheck(cookieSessionId);

    // valid param user, client login
    if (ssoUser == null) {
      // remove old cookie
      SsoLoginHelper.removeSessionIdInCookie(req, res);
      // set new cookie
      String paramSessionId = request.getParameter(Constant.SSO_SESSIONID);
      if (paramSessionId != null) {
        ssoUser = SsoLoginHelper.loginCheck(paramSessionId);
        if (ssoUser != null) {
          SsoLoginHelper.setSessionIdInCookie(res, paramSessionId);
        }
      }
    }

    // valid login fail
    if (ssoUser == null) {
      String header = req.getHeader("content-type");
      boolean isJson = header != null && header.contains("json");
      if (isJson) {
        // json msg
        res.setContentType("application/json;charset=utf-8");
        res.getWriter().println(JacksonUtil.writeValueAsString(Constant.SSO_LOGIN_FAIL_RESULT));
        return;
      } else {
        // redirect logout
        String loginPageUrl = ssoServer.concat(Constant.SSO_LOGIN) + "?" + Constant.REDIRECT_URL + "=" + link;
        res.sendRedirect(loginPageUrl);
        return;
      }
    }

    // ser sso user
    request.setAttribute(Constant.SSO_USER, ssoUser);
    // already login, allow
    chain.doFilter(request, response);
    return;
  }

}

package com.glodon.sso.core.filter;

import com.glodon.sso.core.constant.Constant;
import com.glodon.sso.core.entity.SsoResponse;
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
 * app sso filter
 *
 * @author litz-a
 */
public class GcrSsoTokenFilter extends HttpServlet implements Filter {

  private static Logger logger = LoggerFactory.getLogger(GcrSsoTokenFilter.class);

  private String ssoServer;
  private String logoutPath;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

    ssoServer = filterConfig.getInitParameter(Constant.SSO_SERVER);
    if (ssoServer != null && ssoServer.trim().length() > 0) {
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

    String sessionid = SsoLoginHelper.cookieSessionIdGetByHeader(req);
    SsoUser ssoUser = SsoLoginHelper.loginCheck(sessionid);

    // logout filter
    if (StringUtils.isNotEmpty(logoutPath)
        && logoutPath.equals(servletPath)) {

      if (ssoUser != null) {
        SsoLoginHelper.logout(sessionid);
      }

      // response
      res.setStatus(HttpServletResponse.SC_OK);
      res.setContentType("application/json;charset=UTF-8");
      res.getWriter().println(JacksonUtil.writeValueAsString(new SsoResponse<>(SsoResponse.SUCCESS_CODE, null)));
      return;
    }

    // login filter
    if (ssoUser == null) {
      // response
      res.setStatus(HttpServletResponse.SC_OK);
      res.setContentType("application/json;charset=UTF-8");
      res.getWriter().println(JacksonUtil.writeValueAsString(Constant.SSO_LOGIN_FAIL_RESULT));
      return;
    }
    // ser sso user
    request.setAttribute(Constant.SSO_USER, ssoUser);
    // already login, allow
    chain.doFilter(request, response);
    return;
  }

}

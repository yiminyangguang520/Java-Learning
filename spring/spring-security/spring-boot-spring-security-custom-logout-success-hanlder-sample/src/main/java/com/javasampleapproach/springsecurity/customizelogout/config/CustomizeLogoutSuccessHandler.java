package com.javasampleapproach.springsecurity.customizelogout.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    // Code For Business Here
    logger.info("Logout Sucessfull with Principal: " + authentication.getName());

    response.setStatus(HttpServletResponse.SC_OK);
    //redirect to login
    response.sendRedirect("/logoutsuccessful");
  }

}
package com.javadeveloperzone.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


/**
 * @author min
 * Created by JavaDeveloperZone on 13-11-2017. Spring Security will send control to CustomAuthenticationFailureHandler when authentication will get failed
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws
      IOException, ServletException {
    // write your custom code here
    response.sendRedirect("/loginFailed");
  }
}

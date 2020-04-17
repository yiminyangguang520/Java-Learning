package com.lee.authlogin.config;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
      , Authentication authentication) throws IOException {
    logger.info("User: " + request.getParameter("username") + " Login successfully.");
    this.returnJson(response);
  }

  private void returnJson(HttpServletResponse response) throws IOException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    response.getWriter().println("{\"exceptionId\":\"null\",\"messageCode\":\"200\"," +
        "\"message\": \"Login successfully.\",\"serverTime\": " + System.currentTimeMillis() + "}");
  }
}

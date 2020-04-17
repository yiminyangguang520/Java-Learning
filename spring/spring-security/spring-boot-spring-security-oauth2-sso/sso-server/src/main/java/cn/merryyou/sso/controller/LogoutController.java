package cn.merryyou.sso.controller;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author min
 */
@Controller
public class LogoutController {

  @Autowired
  private TokenStore tokenStore;

  @Autowired
  private ConsumerTokenServices consumerTokenServices;

  @RequestMapping("/exit")
  public void exit(HttpServletRequest request, HttpServletResponse response) {
    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      String value = request.getHeader(key);
      System.out.println(key + " ============================== " + value);
    }
    // token can be revoked here if needed
    String token = request.getHeader("bearer ");
    if (token != null && token.startsWith("authorization")) {
      OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token.split(" ")[1]);
      if (oAuth2AccessToken != null) {
        tokenStore.removeAccessToken(oAuth2AccessToken);
      }
    }

    new SecurityContextLogoutHandler().logout(request, null, null);
    try {
      //sending back to client app
      response.sendRedirect(request.getHeader("referer"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
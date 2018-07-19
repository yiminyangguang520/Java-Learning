package com.glodon.sso.server.controller;

import com.glodon.sso.core.entity.SsoResponse;
import com.glodon.sso.core.user.SsoUser;
import com.glodon.sso.core.util.SsoLoginHelper;
import com.glodon.sso.server.constant.Domain;
import com.glodon.sso.server.service.UserService;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sso server (for app)
 *
 * @author litz-a
 */
@RestController
@RequestMapping("/app")
public class AppController {

  @Autowired
  private UserService userService;

  /**
   * Login
   */
  @RequestMapping("/login")
  public SsoResponse<String> login(String username, String password) {
    if (StringUtils.isBlank(username)) {
      return new SsoResponse<>(SsoResponse.FAIL_CODE, "Please input username.");
    }

    if (StringUtils.isBlank(password)) {
      return new SsoResponse<>(SsoResponse.FAIL_CODE, "Please input password.");
    }

    String result = Domain.DOMAIN_ACCOUNT_AUTHENTICATE_RESULT_FAIL;
    try {
      result = userService.domainAccountAuthenticate(username, password);
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (!StringUtils.equals(result, Domain.DOMAIN_ACCOUNT_AUTHENTICATE_RESULT_PASS)) {
      return new SsoResponse<>(SsoResponse.FAIL_CODE, "username is invalid.");
    }

    SsoUser ssoUser = new SsoUser();
    ssoUser.setUsername(username);

    String sessionId = UUID.randomUUID().toString();
    SsoLoginHelper.login(sessionId, ssoUser);

    return new SsoResponse<>(sessionId);
  }


  @RequestMapping("/logout")
  public SsoResponse<String> logout(String sessionId) {
    SsoLoginHelper.logout(sessionId);
    return SsoResponse.SUCCESS;
  }

  @RequestMapping("/logincheck")
  public SsoResponse<SsoUser> logincheck(String sessionId) {
    SsoUser ssoUser = SsoLoginHelper.loginCheck(sessionId);
    if (ssoUser == null) {
      return new SsoResponse<>(SsoResponse.FAIL_CODE, "sso not login.");
    }

    return new SsoResponse<>(ssoUser);
  }

}
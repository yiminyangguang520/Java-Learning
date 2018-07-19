package com.glodon.sso.core.constant;

import com.glodon.sso.core.entity.SsoResponse;

/**
 * 常量
 * @author litz-a
 */
public class Constant {

  /**
   * redirect url, for client
   */
  public static final String REDIRECT_URL = "redirect_url";

  /**
   * sso sessionid, between browser and sso-server
   */
  public static final String SSO_SESSIONID = "gcr_sso_sessionid";

  /**
   * sso user
   */
  public static final String SSO_USER = "gcr_sso_user";

  /**
   * sso server address
   */
  public static final String SSO_SERVER = "sso_server";

  /**
   * login url
   */
  public static final String SSO_LOGIN = "/login";
  /**
   * logout url
   */
  public static final String SSO_LOGOUT = "/logout";


  /**
   * filter logout path
   */
  public static final String SSO_LOGOUT_PATH = "logoutPath";

  /**
   *
   */
  public static final SsoResponse<String> SSO_LOGIN_FAIL_RESULT = new SsoResponse(501, "sso not login.");


}

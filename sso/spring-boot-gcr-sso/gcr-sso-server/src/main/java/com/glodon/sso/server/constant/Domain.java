package com.glodon.sso.server.constant;

/**
 * @author litz-a
 */
public class Domain {

  /**
   * 域账户认证地址
   */
  public static final String DOMAIN_ACCOUNT_AUTHENTICATE_URL = "http://home.glodon.com:7783/Utilities/Account/ProxyService/SsoAuthUserProcess";

  /**
   * 认证协议
   */
  public static final String DOMAIN_ACCOUNT_AUTHENTICATE_REQUEST_BODY =
      "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sso=\"http://www.glodon.com/xsd/SsoAuthUser\">\n"
          + "   <soapenv:Header/>\n"
          + "   <soapenv:Body>\n"
          + "      <sso:SsoAuthUserRequest>\n"
          + "         <sso:userCode>{0}</sso:userCode>\n"
          + "         <sso:password><![CDATA[{1}]]></sso:password>\n"
          + "      </sso:SsoAuthUserRequest>\n"
          + "   </soapenv:Body>\n"
          + "</soapenv:Envelope>";
  /**
   * 登陆成功
   */
  public static final String DOMAIN_ACCOUNT_AUTHENTICATE_RESULT_PASS = "Y";

  /**
   * 用户名或密码错误
   */
  public static final String DOMAIN_ACCOUNT_AUTHENTICATE_RESULT_ERROR = "N";

  /**
   * 请求失败
   */
  public static final String DOMAIN_ACCOUNT_AUTHENTICATE_RESULT_FAIL = "F";

  /**
   * 账户锁定
   */
  public static final String DOMAIN_ACCOUNT_AUTHENTICATE_RESULT_LOCK = "L";
}

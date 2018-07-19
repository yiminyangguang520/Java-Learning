package com.xxl.app.sample.test;

import com.glodon.sso.core.constant.Constant;
import com.glodon.sso.core.util.JacksonUtil;
import com.xxl.app.sample.test.util.HttpClientUtil;
import java.util.HashMap;
import java.util.Map;
import org.apache.tomcat.util.bcel.Const;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuxueli 2018-04-09 11:38:15
 */
public class TokenClientTest {

  public static String ssoServer = "http://demo.baomidou.com:8080/xxl-sso-server";
  public static String client01 = "http://xxlssoclient1.com:8082/xxl-sso-token-sample-springboot/";
  public static String client02 = "http://xxlssoclient2.com:8082/xxl-sso-token-sample-springboot/";
  private static Logger logger = LoggerFactory.getLogger(TokenClientTest.class);

  @Test
  public void test() throws Exception {

    // 登录：获取 sso sessionId
    String sessionId = loginTest();
    Assert.assertNotNull(sessionId);

    // 登陆状态校验
    String username = logincheckTest(sessionId);
    Assert.assertNotNull(username);

    clientApiRequestTest(client01, sessionId);
    clientApiRequestTest(client02, sessionId);

    // 注销：销毁 sso sessionId
    boolean loginoutResult = logoutTest(sessionId);
    Assert.assertTrue(loginoutResult);

    // 登陆状态校验
    username = logincheckTest(sessionId);
    Assert.assertNull(username);

    clientApiRequestTest(client01, sessionId);
    clientApiRequestTest(client02, sessionId);
  }

  /**
   * Client API Request, SSO APP Filter
   */
  private void clientApiRequestTest(String clientApiUrl, String sessionId) {

    Map<String, String> headerParam = new HashMap<>();
    headerParam.put(Constant.SSO_SESSIONID, sessionId);

    String resultJson = HttpClientUtil.post(clientApiUrl, null, headerParam);
    Map<String, Object> loginResult = JacksonUtil.readValue(resultJson, Map.class);

    int code = (int) loginResult.get("code");
    if (code == 200) {
      Map user = (Map) loginResult.get("data");
      String username = (String) user.get("username");
      logger.info("模拟请求APP应用接口，请求成功，登陆用户 = " + username);
    } else {
      String failMsg = (String) loginResult.get("msg");
      logger.info("模拟请求APP应用接口，请求失败：" + failMsg);
    }
  }

  /**
   * SSO Login
   */
  private String loginTest() {
    // login url
    String loginUrl = ssoServer + "/app/login";

    // login param
    Map<String, String> loginParam = new HashMap<>();
    loginParam.put("username", "admin");
    loginParam.put("password", "admin");

    String loginResultJson = HttpClientUtil.post(loginUrl, loginParam, null);
    Map<String, Object> loginResult = JacksonUtil.readValue(loginResultJson, Map.class);

    int code = (int) loginResult.get("code");
    if (code == 200) {
      String sessionId = (String) loginResult.get("data");
      logger.info("登录成功，sessionid = " + sessionId);
      return sessionId;
    } else {
      String failMsg = (String) loginResult.get("msg");
      logger.info("登录失败：" + failMsg);
      return null;
    }

  }

  /**
   * SSO Logout
   */
  private boolean logoutTest(String sessionId) {
    // logout url
    String logoutUrl = ssoServer + "/app/logout";

    // logout param
    Map<String, String> logoutParam = new HashMap<>();
    logoutParam.put("sessionId", sessionId);

    String logoutResultJson = HttpClientUtil.post(logoutUrl, logoutParam, null);
    Map<String, Object> logoutResult = JacksonUtil.readValue(logoutResultJson, Map.class);

    int code = (int) logoutResult.get("code");
    if (code == 200) {
      logger.info("注销成功");
      return true;
    } else {
      String failMsg = (String) logoutResult.get("msg");
      logger.info("注销失败：" + failMsg);
      return false;
    }

  }

  /**
   * SSO Login Check
   */
  private String logincheckTest(String sessionId) {
    // logout url
    String logincheckUrl = ssoServer + "/app/logincheck";

    // logout param
    Map<String, String> logincheckParam = new HashMap<>();
    logincheckParam.put("sessionId", sessionId);

    String logincheckResultJson = HttpClientUtil.post(logincheckUrl, logincheckParam, null);
    Map<String, Object> logincheckResult = JacksonUtil.readValue(logincheckResultJson, Map.class);

    int code = (int) logincheckResult.get("code");
    if (code == 200) {
      Map user = (Map) logincheckResult.get("data");
      String username = (String) user.get("username");
      logger.info("当前为登录状态，登陆用户 = " + username);
      return username;
    } else {
      logger.info("当前为注销状态");
      return null;
    }
  }

}
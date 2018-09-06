package com.lee.resttemple.utils;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @author litz-a
 */
public class BasicHeaderBuilder {

  public static final String SERVICE_KEY = "kBuJzyH70hcm7SFUwALyHjsFKtnUI4gl";
  public static final String SERVER_SECRET = "NAq5zQumk9VN5zUdGG6PehshEw5a7dhH";

  /**
   * 创建Basic认证头信息中的Key
   */
  public static String headerKey() {
    return "Authorization";
  }

  /**
   * 创建Basic认证头信息中的Value
   */
  public static String headerValue() {
    String credential = SERVICE_KEY + ":" + SERVER_SECRET;
    String encoder = Base64.encodeBase64String(credential.getBytes());
    return "Basic " + encoder;
  }
}
package org.shiro.security.common.utils;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:48:13
 * 类说明：Redis所有Keys
 */
public class RedisKeys {

  public static String getSysConfigKey(String key) {
    return "sys:config:" + key;
  }

  public static String getShiroSessionKey(String key) {
    return "sessionid:" + key;
  }
}

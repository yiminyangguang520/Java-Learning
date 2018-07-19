package com.glodon.sso.core.store;

import com.glodon.sso.core.constant.Constant;
import com.glodon.sso.core.user.SsoUser;
import com.glodon.sso.core.util.RedisPoolUtil;
import com.google.gson.Gson;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
public class SsoLoginStore {

  public static SsoUser get(String sessionId) {
    String redisKey = redisKey(sessionId);
    String json = RedisPoolUtil.get(redisKey);
    return new Gson().fromJson(json, SsoUser.class);
  }

  /**
   * remove
   */
  public static long remove(String sessionId) {
    String redisKey = redisKey(sessionId);
    return RedisPoolUtil.del(redisKey);
  }

  /**
   * put
   */
  public static void put(String sessionId, SsoUser ssoUser) {
    String redisKey = redisKey(sessionId);
    RedisPoolUtil.set(redisKey, ssoUser);
  }

  private static String redisKey(String sessionId) {
    return Constant.SSO_SESSIONID.concat("#").concat(sessionId);
  }

}

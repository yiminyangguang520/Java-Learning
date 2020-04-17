package com.itstyle.jwt.service.impl;

import com.itstyle.jwt.constant.Constant;
import com.itstyle.jwt.model.User;
import com.itstyle.jwt.service.TokenManager;
import com.itstyle.jwt.util.JwtUtils;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 通过Redis存储和验证token的实现类
 * @author min
 */
@Component
public class RedisTokenManagerImpl implements TokenManager {

  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  public String createToken(User user) {
    String userId = String.valueOf(user.getId());

    String JWT = JwtUtils.createJWT(userId, user.getUsername(), Constant.JWT_TTL);
    //存储到redis并设置过期时间
    redisTemplate.boundValueOps(userId).set(JWT, Constant.JWT_TTL, TimeUnit.MILLISECONDS);
    return JWT;
  }

  /**
   * 获取token
   */
  @Override
  public String getToken(User user) {
    String userId = String.valueOf(user.getId());
    if (redisTemplate.hasKey(userId)) {
      String token = (String) redisTemplate.boundValueOps(userId).get();
      return token;
    }
    return StringUtils.EMPTY;
  }

  @Override
  public void deleteToken(long userId) {
    redisTemplate.delete(String.valueOf(userId));
  }
}
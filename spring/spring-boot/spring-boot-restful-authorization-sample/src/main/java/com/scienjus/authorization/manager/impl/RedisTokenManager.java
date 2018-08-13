package com.scienjus.authorization.manager.impl;

import com.scienjus.authorization.manager.TokenManager;
import com.scienjus.authorization.model.TokenModel;
import com.scienjus.config.Constants;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 通过Redis存储和验证token的实现类
 *
 * @author ScienJus
 * @date 2015/7/31.
 * @see com.scienjus.authorization.manager.TokenManager
 */
@Component
public class RedisTokenManager implements TokenManager {

  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  public TokenModel createToken(long userId) {
    //使用uuid作为源token
    String token = UUID.randomUUID().toString().replace("-", "");
    TokenModel model = new TokenModel(userId, token);
    //存储到redis并设置过期时间
    redisTemplate.boundValueOps(String.valueOf(userId)).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
    return model;
  }

  @Override
  public TokenModel getToken(String authentication) {
    if (authentication == null || authentication.length() == 0) {
      return null;
    }
    String[] param = authentication.split("_");
    if (param.length != 2) {
      return null;
    }
    //使用userId和源token简单拼接成的token，可以增加加密措施
    long userId = Long.parseLong(param[0]);
    String token = param[1];
    return new TokenModel(userId, token);
  }

  @Override
  public boolean checkToken(TokenModel model) {
    if (model == null) {
      return false;
    }
    String token = (String) redisTemplate.boundValueOps(String.valueOf(model.getUserId())).get();
    if (token == null || !token.equals(model.getToken())) {
      return false;
    }
    //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
    redisTemplate.boundValueOps(String.valueOf(model.getUserId())).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
    return true;
  }

  @Override
  public void deleteToken(long userId) {
    redisTemplate.delete(String.valueOf(userId));
  }
}

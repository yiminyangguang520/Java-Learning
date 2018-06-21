package org.shiro.security.modules.sys.redis;


import org.shiro.common.utils.RedisUtils;
import org.shiro.security.common.utils.RedisKeys;
import org.shiro.security.modules.sys.entity.SysConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:09:50
 * 类说明：系统配置Redis
 */
@Component
public class SysConfigRedis {

  @Autowired
  private RedisUtils redisUtils;

  public void saveOrUpdate(SysConfigEntity config) {
    if (config == null) {
      return;
    }
    String key = RedisKeys.getSysConfigKey(config.getParamKey());
    redisUtils.set(key, config);
  }

  public void delete(String configKey) {
    String key = RedisKeys.getSysConfigKey(configKey);
    redisUtils.delete(key);
  }

  public SysConfigEntity get(String configKey) {
    String key = RedisKeys.getSysConfigKey(configKey);
    return redisUtils.get(key, SysConfigEntity.class);
  }
}

package com.shihuc.dbconn.dao.redis;

/**
 * Created by chengsh05 on 2017/7/19.
 * @author litz-a
 */
public interface IRedisService {

  /**
   * 设置值
   * @param key
   * @param value
   * @param expireTime
   * @return
   */
  boolean putValue(String key, Object value, long expireTime);

  /**
   * 获取值
   * @param key
   * @return
   */
  Object getValue(String key);
}

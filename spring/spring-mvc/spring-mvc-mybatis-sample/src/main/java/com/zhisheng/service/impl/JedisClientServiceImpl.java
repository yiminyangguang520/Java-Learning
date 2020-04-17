package com.zhisheng.service.impl;

import com.zhisheng.model.User;
import com.zhisheng.service.IJedisClientService;
import com.zhisheng.util.JsonUtils;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author min
 * Created by 10412 on 2017/8/9.
 */
@Service
public class JedisClientServiceImpl implements IJedisClientService {

  @Autowired
  private JedisPool jedisPool;

  /**
   * 设置过期时间为两天
   */
  private int expiredTime = 60 * 60 * 24 * 2;


  @Override
  public String get(String key) {
    Jedis jedis = jedisPool.getResource();
    String s = jedis.get(key);
    //去掉注释机可以有过期策略
//        jedis.expire(key, 5);
    jedis.close();
    return s;
  }

  @Override
  public String set(String key, String value) {
    Jedis jedis = jedisPool.getResource();
    String s = jedis.set(key, value);
    //去掉注释机可以有过期策略
//        jedis.expire(key, 5);
//        System.out.println("key:------ " + key)
//        System.out.println("查看key的剩余生存时间："+jedis.ttl(key));
    jedis.close();
    return s;
  }

  @Override
  public String hget(String hkey, String key) {
    System.out.println("jedispool: +++++++    " + jedisPool);
    Jedis jedis = jedisPool.getResource();
    System.out.println("jedis  ------- " + jedis);
    String s = jedis.hget(hkey, key);
    jedis.close();
    return s;
  }

  @Override
  public long hset(String hkey, String key, String value) {
    System.out.println("jedispool ----- " + jedisPool);
    Jedis jedis = jedisPool.getResource();
    System.out.println("jedis ----  " + jedis);
    Long l = jedis.hset(hkey, key, value);
    //解开注释即可体验过期策略
//        jedis.expire(hkey,expiredTime);
//        System.out.println("key :  "+key);
//        System.out.println("查看key001的剩余生存时间："+jedis.ttl(hkey));
    jedis.close();
    return l;
  }

  @Override
  public long incr(String key) {
    Jedis jedis = jedisPool.getResource();
    Long l = jedis.incr(key);
    jedis.close();
    return l;
  }

  @Override
  public long expire(String key, int second) {
    Jedis jedis = jedisPool.getResource();
    Long l = jedis.expire(key, second);
    jedis.close();
    return l;
  }

  @Override
  public long ttl(String key) {
    Jedis jedis = jedisPool.getResource();
    Long l = jedis.ttl(key);
    jedis.close();
    return l;
  }

  @Override
  public long del(String key) {
    Jedis jedis = jedisPool.getResource();
    Long l = jedis.del(key);
    jedis.close();
    return l;
  }

  @Override
  public long hdel(String hkey, String key) {
    Jedis jedis = jedisPool.getResource();
    Long l = jedis.hdel(hkey, key);
    jedis.close();
    return l;
  }

  //单个用户加入。而且可用于更新操作！！！！
  @Override
  public long zadd(String key, double score, User user) {
    Jedis jedis = jedisPool.getResource();
    Long l = jedis.zadd(key, score, JsonUtils.objectToJson(user));
    jedis.close();
    return 0;
  }

  //全拿榜单信息（拿多少就多少。）
  @Override
  public Set<String> zgetAll(String key, long start, long end) {
    Jedis jedis = jedisPool.getResource();
    Set<String> userSet = jedis.zrange(key, start, end);
    System.out.println("userSet:  -----" + userSet);
    jedis.close();
    return userSet;
  }

  //批量加入用户到redis
  @Override
  public long zaddList(String key, List<User> userList) {
    Jedis jedis = jedisPool.getResource();
    Long result = null;
    for (int i = 0; i < userList.size(); i++) {
      result = jedis.zadd(key, userList.get(i).getScore(), JsonUtils.objectToJson(userList.get(i)));
    }
    jedis.close();
    return result;
  }

  //拿最后一名的，所以start跟end必须标记最后一名的位置索引
  @Override
  public Set<String> getTopLast(String key, long start, long end) {
    Jedis jedis = jedisPool.getResource();
    Set<String> userSet = jedis.zrange(key, start, end);
    System.out.println("userSet:  ------  " + userSet);
    jedis.close();
    return userSet;
  }

}

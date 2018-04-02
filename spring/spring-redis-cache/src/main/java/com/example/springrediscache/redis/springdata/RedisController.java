package com.example.springrediscache.redis.springdata;

import com.example.springrediscache.redis.entity.User;
import com.example.springrediscache.redis.helper.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class RedisController {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Autowired
  private RedisTemplate redisTemplate;

  @RequestMapping(value = "/redis/string", method = RequestMethod.GET)
  public void insertString() {
    stringRedisTemplate.opsForValue().set("stringKey", "stringValue");
  }

  @RequestMapping(value = "/redis/string/object", method = RequestMethod.GET)
  public void insertStringObject() {
    User user = new User();
    user.setUserId(1);
    user.setUsername("user1");
    user.setPassword("password1");
    redisTemplate.opsForValue().set("stringKeyObject", user);
  }

  @RequestMapping(value = "/redis/string/object/get", method = RequestMethod.GET)
  public User getStringObject() {
    User user = (User) redisTemplate.opsForValue().get("stringKeyObject");
    return user;
  }

  @CachePut(value = "user", key = "#root.caches[0].name + ':' + #user.userId")
  @RequestMapping(value = "/redis/user", method = RequestMethod.POST)
  public User insertUser(@RequestBody User user) {
    user.setPassword(SystemUtil.MD5(user.getPassword()));
    return user;
  }

  @Cacheable(value = "user")
  @RequestMapping(value = "/redis/user/{userId}", method = RequestMethod.GET)
  public User getUser(@PathVariable Integer userId) {
    return new User(userId, "Bruce", "cm");
  }

  /**
   * #root.caches[0].name:当前被调用方法所使用的Cache, 即"user"
   * @param user
   * @return
   */
  @CachePut(value = "user", key = "#root.caches[0].name + ':' + #user.userId")
  @RequestMapping(value = "/redis/user", method = RequestMethod.PUT)
  public User updateUser(@RequestBody User user) {
    user.setPassword(SystemUtil.MD5(user.getPassword()));
    return user;
  }

  @CacheEvict(value = "user")
  @RequestMapping(value = "/redis/user/{userId}", method = RequestMethod.DELETE)
  public void deleteUser(@PathVariable Integer userId) {

  }

}

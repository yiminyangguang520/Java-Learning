package com.example.webfluxsessiondemo.repo;

import com.example.webfluxsessiondemo.model.User;
import com.google.common.collect.Maps;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * @author wangxing
 * @create 2019/5/15
 */
@Service
public class UserRepo {

  private Map<String, User> userMap = Maps.newConcurrentMap();

  public void save(User user) {
    userMap.put(user.getUserName(), user);
  }

  @Synchronized
  public User getByUserName(String userName) {
    User user = userMap.get(userName);
    if (user == null) {
      user = new User();
      user.setUserName(userName);
      user.setId(UUID.randomUUID().toString());
      save(user);
    }
    return user;
  }

}
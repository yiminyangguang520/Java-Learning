package com.example.service.impl;

import com.example.dao.UserMapper;
import com.example.entity.User;
import com.example.entity.UserExample;
import com.example.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  /**
   * 根据用户名查找
   */
  @Override
  public User findByUserName(String userName) {
    UserExample userExample = new UserExample();
    userExample.createCriteria().andUsernameEqualTo(userName);
    List<User> userList = userMapper.selectByExample(userExample);
    if (userList.size() > 0) {
      return userList.get(0);
    }

    return null;
  }

  /**
   * 插入用户
   */
  @Override
  public int inserUser(User user) {
    return userMapper.insertSelective(user);
  }
}

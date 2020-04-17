package com.example.service;

import com.example.entity.User;

/**
 * @author min
 */
public interface UserService {

  /**
   * 根据用户名查找
   * @param userName
   * @return
   */
  User findByUserName(String userName);

  /**
   * 插入用户
   * @param user
   * @return
   */
  int inserUser(User user);
}

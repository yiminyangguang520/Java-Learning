package com.room.dao;

import com.room.entity.User;

/**
 * @author min
 * DAO接口,mybatis动态完成Impl
 */
public interface UserDao {

  /**
   * 根据用户名查找用户登录相关信息
   * @param name
   * @return
   */
  User findByName(String name);

  /**
   * 添加用户
   * @param user
   */
  void addUser(User user);
}

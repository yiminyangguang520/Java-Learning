package com.xiaoze.api.dao;


import com.xiaoze.api.entity.User;

/**
 * UserService
 *
 * @author xiaoze
 * @date 2018/6/3
 */
public interface UserDao {

  /**
   * 获取一条用户数据
   *
   * @return User
   */
  User get(String userNo);


}


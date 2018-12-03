package com.xiaoze.provider.mapper;


import com.xiaoze.api.entity.User;

/**
 * UserMapper
 *
 * @author xiaoze
 * @date 2018/6/11
 */
public interface UserMapper {

  /**
   * 获取一条用户数据
   *
   * @return User
   */
  User get(String userNo);

  void addUser(User user);

}

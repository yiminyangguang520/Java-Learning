package com.itstyle.jwt.service;

import com.itstyle.jwt.model.User;

/**
 * 对Token进行操作的接口
 * @author min
 */
public interface TokenManager {

  /**
   * 创建一个token关联上指定用户
   *
   * @param user
   * @return 生成的token
   */
  String createToken(User user);


  /**
   * 获取token
   * @param user
   * @return
   */
  String getToken(User user);

  /**
   * 清除token
   *
   * @param userId 登录用户的id
   */
  void deleteToken(long userId);
}
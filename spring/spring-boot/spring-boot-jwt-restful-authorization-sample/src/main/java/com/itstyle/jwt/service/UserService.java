package com.itstyle.jwt.service;

import com.itstyle.jwt.model.User;

/**
 * @author min
 */
public interface UserService {

  /**
   * 账户认证
   * @param account
   * @param password
   * @return
   * @throws Exception
   */
  String accountAuthenticate(String account, String password) throws Exception;

  /**
   * save user
   * @param username
   * @return
   */
  User saveUser(String username);

  /**
   * findByUserName
   * @param username
   * @return
   */
  User findByUserName(String username);

  /**
   * getOrCreateUser
   * @param username
   * @return
   */
  User getOrCreateUser(String username);
}

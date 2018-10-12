package com.example.service;

import com.example.model.User;

/**
 * @author litz-a
 */
public interface UserService {

  /**
   * findUserByEmail
   * @param email
   * @return
   */
  User findUserByEmail(String email);

  /**
   * saveUser
   * @param user
   */
  void saveUser(User user);
}

package com.us.example.dao;

import com.us.example.domain.User;


/**
 * @author litz-a
 */
public interface UserDao {

  /**
   * findByUserName
   * @param username
   * @return
   */
  User findByUserName(String username);

  /**
   * create
   * @param user
   * @return
   */
  int create(User user);
}

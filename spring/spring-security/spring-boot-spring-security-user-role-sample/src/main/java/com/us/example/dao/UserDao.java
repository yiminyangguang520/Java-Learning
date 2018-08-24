package com.us.example.dao;

import com.us.example.domain.SysUser;


/**
 * @author litz-a
 */
public interface UserDao {

  /**
   * findByUserName
   * @param username
   * @return
   */
  SysUser findByUserName(String username);
}

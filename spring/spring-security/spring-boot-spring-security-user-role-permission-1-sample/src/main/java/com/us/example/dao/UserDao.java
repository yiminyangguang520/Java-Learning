package com.us.example.dao;

import com.us.example.domain.SysUser;


/**
 * @author min
 */
public interface UserDao {

  /**
   * findByUserName
   * @param username
   * @return
   */
  SysUser findByUserName(String username);
}

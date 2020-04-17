package com.lee.security;

import com.lee.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * 用户详细信息类
 * 负责以{@link UserDetails}方式提供用户信息
 * @author min
 */
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserDao userDao = new UserDao();

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userDao.getUser(username);
  }
}

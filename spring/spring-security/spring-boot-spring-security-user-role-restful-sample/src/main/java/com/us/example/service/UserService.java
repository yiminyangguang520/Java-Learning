package com.us.example.service;

import com.us.example.dao.UserDao;
import com.us.example.domain.User;
import com.us.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class UserService {

  @Autowired
  private UserDao userDao;

  public User create(User user) {
    //进行加密
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    user.setPassword(encoder.encode(MD5Util.encode(user.getRawPassword().trim())));
    userDao.create(user);
    return user;
  }

}

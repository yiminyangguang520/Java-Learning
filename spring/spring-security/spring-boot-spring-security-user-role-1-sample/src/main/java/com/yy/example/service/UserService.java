package com.yy.example.service;

import com.yy.example.bean.User;
import com.yy.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;


/**
 * @author litz-a
 */
@Service
public class UserService {

  @Autowired
  private SessionRegistry sessionRegistry;

  @Autowired
  private UserDao userDao;

  public User getById(Integer id) {
    return userDao.getById(id);
  }

}
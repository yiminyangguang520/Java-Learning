package com.xiaoze.consumer.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoze.api.dao.UserDao;
import com.xiaoze.api.entity.User;
import com.xiaoze.consumer.service.UserService;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author xiaoze
 * @date 2018/6/12
 */
@Service
public class UserServiceImpl implements UserService {

  @Reference(version = "${demo.service.version}",
      application = "${dubbo.application.id}",
      url = "dubbo://localhost:20880")
  private UserDao userDao;

  @Override
  public User get(String userNo) {

    User user = null;
    user = userDao.get(userNo);
    return user;

  }


}
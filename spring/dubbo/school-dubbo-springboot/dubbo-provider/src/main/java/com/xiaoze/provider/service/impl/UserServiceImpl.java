package com.xiaoze.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoze.api.dao.UserDao;
import com.xiaoze.api.entity.User;
import com.xiaoze.provider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserServiceImpl
 *
 * @author xiaoze
 * @date 2018/6/11
 */
@Service(version = "${demo.service.version}",
    application = "${dubbo.application.id}",
    protocol = "${dubbo.protocol.id}",
    registry = "${dubbo.registry.id}"
)
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserDao {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User get(String userNo) {

    User user = null;
    user = userMapper.get(userNo);
    return user;

  }

}
package com.zhisheng.service.impl;

import com.zhisheng.dao.UserDao;
import com.zhisheng.model.User;
import com.zhisheng.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 10412 on 2017/8/10.
 */
@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  private UserDao userDao;

  /**
   * 根据id查询用户
   */
  @Override
  public User queryById(long id) {
    User user = userDao.queryById(id);
    return user;
  }

  /**
   * 查询所有的用户信息
   */
  @Override
  public List<User> queryAll() {
    List<User> users = userDao.queryAll();
    return users;
  }

  /**
   * 根据id删除用户
   */
  @Override
  public int deleteUser(long id) {
    int i = userDao.deleteUser(id);
    return i;
  }

  /**
   * 插入用户
   */
  @Override
  public int insertUser(User user) {
    int i = userDao.insertUser(user);
    return i;
  }

  /**
   * 排行榜的查找topN
   */
  @Override
  public List<User> queryTopN() {
    List<User> users = userDao.queryTopN();
    return users;
  }
}

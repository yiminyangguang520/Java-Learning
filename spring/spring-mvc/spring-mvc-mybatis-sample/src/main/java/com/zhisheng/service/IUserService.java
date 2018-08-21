package com.zhisheng.service;

import com.zhisheng.model.User;
import java.util.List;

/**
 * Created by 10412 on 2017/8/10.
 */
public interface IUserService {

  /**
   * 根据id查询用户
   */
  User queryById(long id);

  /**
   * 查询所有的用户信息
   */
  List<User> queryAll();

  /**
   * 根据id删除用户
   */
  int deleteUser(long id);

  /**
   * 插入用户
   */
  int insertUser(User user);

  /**
   * 排行榜的查找topN
   */
  List<User> queryTopN();

}

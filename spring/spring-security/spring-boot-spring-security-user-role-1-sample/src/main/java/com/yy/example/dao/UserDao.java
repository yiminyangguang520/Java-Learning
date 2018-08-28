package com.yy.example.dao;


import com.yy.example.bean.User;
import java.util.List;
import java.util.Map;

/**
 * @author litz-a
 */
public interface UserDao {

  /**
   * getByMap
   * @param map
   * @return
   */
  List<User> getByMap(Map<String, Object> map);

  /**
   * getById
   * @param id
   * @return
   */
  User getById(Integer id);

  /**
   * create
   * @param user
   * @return
   */
  Integer create(User user);

  /**
   * update
   * @param user
   * @return
   */
  int update(User user);

  /**
   * getByUserName
   * @param userName
   * @return
   */
  User getByUserName(String userName);
}
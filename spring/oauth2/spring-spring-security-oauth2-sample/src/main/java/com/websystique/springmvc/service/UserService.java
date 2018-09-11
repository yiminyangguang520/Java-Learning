package com.websystique.springmvc.service;

import com.websystique.springmvc.model.User;
import java.util.List;


/**
 * @author litz-a
 */
public interface UserService {

  /**
   * findById
   * @param id
   * @return
   */
  User findById(long id);

  /**
   * findByName
   * @param name
   * @return
   */
  User findByName(String name);

  /**
   * saveUser
   * @param user
   */
  void saveUser(User user);

  /**
   * updateUser
   * @param user
   */
  void updateUser(User user);

  /**
   * deleteUserById
   * @param id
   */
  void deleteUserById(long id);

  /**
   * findAllUsers
   * @return
   */
  List<User> findAllUsers();

  /**
   * deleteAllUsers
   */
  void deleteAllUsers();

  /**
   * isUserExist
   * @param user
   * @return
   */
  boolean isUserExist(User user);

}

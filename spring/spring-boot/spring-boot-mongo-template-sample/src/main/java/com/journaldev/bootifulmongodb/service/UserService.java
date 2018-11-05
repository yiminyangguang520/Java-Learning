package com.journaldev.bootifulmongodb.service;

import com.journaldev.bootifulmongodb.model.User;
import java.util.List;

/**
 * @author litz-a
 */
public interface UserService {

  /**
   * getAllUsers
   * @return
   */
  List<User> getAllUsersByExample();

  /**
   * getAllUsers
   * @return
   */
  List<User> getAllUsers();

  /**
   * getUserById
   * @param userId
   * @return
   */
  User getUserById(String userId);

  /**
   * addNewUser
   * @param user
   * @return
   */
  User addNewUser(User user);

  /**
   * getAllUserSettings
   * @param userId
   * @return
   */
  Object getAllUserSettings(String userId);

  /**
   * getUserSetting
   * @param userId
   * @param key
   * @return
   */
  String getUserSetting(String userId, String key);

  /**
   * addUserSetting
   * @param userId
   * @param key
   * @param value
   * @return
   */
  String addUserSetting(String userId, String key, String value);
}
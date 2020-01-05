package com.websystique.springmvc.service;

import com.websystique.springmvc.model.User;
import java.util.List;


/**
 * @author bruce
 */
public interface UserService {

  User findById(long id);

  User findByName(String name);

  void saveUser(User user);

  void updateUser(User user);

  void deleteUserById(long id);

  List<User> findAllUsers();

  void deleteAllUsers();

  boolean isUserExist(User user);

}

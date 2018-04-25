package com.memorynotfound.service;

import com.memorynotfound.model.User;
import java.util.List;

/**
 * @author litz-a
 */
public interface UserService {

  List<User> getAll(int offset, int count);

  User findById(int id);

  User findByName(String name);

  void create(User user);

  void update(User user);

  void delete(int id);

  boolean exists(User user);
}

package com.devglan.service;

import com.devglan.model.User;
import java.util.List;

/**
 * @author bruce
 */
public interface UserService {

  /**
   * save
   * @param user
   * @return
   */
  User save(User user);

  /**
   * findAll
   * @return
   */
  List<User> findAll();

  /**
   * delete
   * @param id
   */
  void delete(long id);
}

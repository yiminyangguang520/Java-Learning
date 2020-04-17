package com.websystique.springsecurity.service;

import com.websystique.springsecurity.model.User;
import java.util.List;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;


/**
 * @author min
 */
public interface UserService {

  /**
   * findAllUsers
   * @return
   */
  List<User> findAllUsers();

  /**
   * findById
   * @param id
   * @return
   */
  @PostAuthorize("returnObject.type == authentication.name")
  User findById(int id);

  /**
   * updateUser
   * @param user
   */
  @PreAuthorize("hasRole('ADMIN')")
  void updateUser(User user);

  /**
   * deleteUser
   * @param id
   */
  @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
  void deleteUser(int id);
}
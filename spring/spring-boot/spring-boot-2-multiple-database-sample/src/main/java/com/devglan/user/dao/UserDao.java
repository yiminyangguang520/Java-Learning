package com.devglan.user.dao;

import com.devglan.model.UserDetails;
import org.springframework.data.repository.CrudRepository;

/**
 * @author litz-a
 */
public interface UserDao extends CrudRepository<UserDetails, Long> {

  /**
   * findByEmail
   * @param email
   * @return
   */
  UserDetails findByEmail(String email);

}

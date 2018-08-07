package com.bfwg.repository;

import com.bfwg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author fan.jin
 * @date 2016-10-15
 */
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * findByUsername
   * @param username
   * @return
   */
  User findByUsername(String username);
}


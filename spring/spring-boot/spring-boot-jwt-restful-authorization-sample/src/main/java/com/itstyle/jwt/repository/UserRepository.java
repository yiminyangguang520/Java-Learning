package com.itstyle.jwt.repository;

import com.itstyle.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户管理
 * @author min
 */
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * findByUsername
   * @param username
   * @return
   */
  User findByUsername(String username);
}

package com.brahalla.cerberus.repository;

import com.brahalla.cerberus.domain.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author litz-a
 */
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * findByUsername
   * @param username
   * @return
   */
  User findByUsername(String username);

}

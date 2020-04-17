package com.auth0.samples.authapi.springbootauthupdated.task.repository;

import com.auth0.samples.authapi.springbootauthupdated.task.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author min
 */
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

  /**
   * findByUsername
   * @param username
   * @return
   */
  ApplicationUser findByUsername(String username);
}
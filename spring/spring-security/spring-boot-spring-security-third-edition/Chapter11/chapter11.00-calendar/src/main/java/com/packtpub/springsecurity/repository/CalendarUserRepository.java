package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.CalendarUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author litz-a
 */
public interface CalendarUserRepository extends JpaRepository<CalendarUser, Integer> {

  /**
   * findByEmail
   * @param email
   * @return
   */
  CalendarUser findByEmail(String email);

}

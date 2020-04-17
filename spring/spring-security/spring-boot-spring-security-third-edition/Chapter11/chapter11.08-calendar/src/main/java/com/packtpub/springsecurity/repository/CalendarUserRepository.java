package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.CalendarUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author min
 */
public interface CalendarUserRepository extends JpaRepository<CalendarUser, Integer> {

  /**
   * findByEamil
   * @param email
   * @return
   */
  CalendarUser findByEmail(String email);

}

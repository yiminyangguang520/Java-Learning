package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.CalendarUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bruce
 */
public interface CalendarUserRepository extends JpaRepository<CalendarUser, Integer> {

  CalendarUser findByEmail(String email);

} // The End...

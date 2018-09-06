package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.CalendarUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author litz-a
 */
public interface CalendarUserRepository extends MongoRepository<CalendarUser, Integer> {

  /**
   * findByEmail
   * @param email
   * @return
   */
  CalendarUser findByEmail(String email);

} // The End...

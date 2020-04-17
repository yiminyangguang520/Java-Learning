package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * findByEmail
   * @param email
   * @return
   */
  User findByEmail(String email);
}

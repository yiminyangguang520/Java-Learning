package com.grokonez.jwtauthentication.repository;

import com.grokonez.jwtauthentication.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author litz-a
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * findByUsername
   * @param username
   * @return
   */
  Optional<User> findByUsername(String username);

  /**
   * existsByUsername
   * @param username
   * @return
   */
  Boolean existsByUsername(String username);

  /**
   * existsByEmail
   * @param email
   * @return
   */
  Boolean existsByEmail(String email);
}
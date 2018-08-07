package com.example.polls.repository;

import com.example.polls.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rajeevkumarsingh
 * @date 02/08/17
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * findByEmail
   * @param email
   * @return
   */
  Optional<User> findByEmail(String email);

  /**
   * findByUsernameOrEmail
   * @param username
   * @param email
   * @return
   */
  Optional<User> findByUsernameOrEmail(String username, String email);

  /**
   * findByIdIn
   * @param userIds
   * @return
   */
  List<User> findByIdIn(List<Long> userIds);

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

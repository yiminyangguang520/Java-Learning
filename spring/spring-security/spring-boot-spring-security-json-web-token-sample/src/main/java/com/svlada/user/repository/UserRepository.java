package com.svlada.user.repository;

import com.svlada.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * UserRepository
 *
 * @author vladimir.stankovic
 *
 * Aug 16, 2016
 */
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * findByUsername
   * @param username
   * @return
   */
  @Query("select u from User u left join fetch u.roles r where u.username=:username")
  Optional<User> findByUsername(@Param("username") String username);
}

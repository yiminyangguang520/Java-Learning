package com.nouhoun.springboot.jwt.integration.repository;

import com.nouhoun.springboot.jwt.integration.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nydiarra on 06/05/17.
 * @author nydiarra
 */
public interface UserRepository extends CrudRepository<User, Long> {

  /**
   * findByUsername
   * @param username
   * @return
   */
  User findByUsername(String username);
}

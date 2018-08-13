package com.scienjus.repository;

import com.scienjus.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * User类的CRUD操作
 *
 * @author ScienJus
 * @date 2015/7/10.
 * @see com.scienjus.domain.User
 */
public interface UserRepository extends CrudRepository<User, Long> {

  /**
   * findByUsername
   * @param username
   * @return
   */
  User findByUsername(String username);
}

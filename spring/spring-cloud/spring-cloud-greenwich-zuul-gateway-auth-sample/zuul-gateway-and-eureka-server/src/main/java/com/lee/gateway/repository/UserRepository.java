package com.lee.gateway.repository;

import com.lee.gateway.bean.auth.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

  /**
   * findByEmail
   * @param email
   * @return
   */
  @Query(value = "{'email' : ?0}")
  User findByEmail(String email);
}

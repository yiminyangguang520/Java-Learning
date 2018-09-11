package com.svlada.security;

import com.svlada.entity.User;
import java.util.Optional;

/**
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */
public interface UserService {

  /**
   * getByUsername
   * @param username
   * @return
   */
  Optional<User> getByUsername(String username);
}

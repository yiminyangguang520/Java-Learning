package com.octoperf.service;

import com.octoperf.security.User;
import java.util.Optional;

/**
 * @author min
 */
public interface UserRegistrationService {

  /**
   * isEnable
   *
   * @return true if registration is enabled
   */
  boolean isEnabled();

  /**
   * In the case the username already exists, it returns the already registered user.
   *
   * @param username
   * @param password
   * @throws IllegalArgumentException if username is empty or already exists
   * @throws IllegalStateException if username is a disposable mail
   * @return
   */
  User register(String username, Optional<String> password);
}
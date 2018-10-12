package com.lee.resttemple.service;

import com.lee.resttemple.model.User;
import com.lee.resttemple.result.AccessToken;
import java.io.IOException;

/**
 * @author litz-a
 */
public interface LoginService {

  /**
   * login
   * @param username
   * @param password
   * @return
   */
  AccessToken login(String username, String password);

  /**
   * login
   * @param user
   * @return
   */
  AccessToken login(User user);

  /**
   * validate
   * @param account
   * @return
   * @throws IOException
   */
  Boolean validate(String account) throws IOException;
}
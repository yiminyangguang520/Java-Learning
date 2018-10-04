package com.nouhoun.springboot.jwt.integration.service;

import com.nouhoun.springboot.jwt.integration.domain.RandomCity;
import com.nouhoun.springboot.jwt.integration.domain.User;
import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 * @author nydiarra
 */
public interface GenericService {

  /**
   * findByUsername
   * @param username
   * @return
   */
  User findByUsername(String username);

  /**
   * findAllUsers
   * @return
   */
  List<User> findAllUsers();

  /**
   * findAllRandomCities
   * @return
   */
  List<RandomCity> findAllRandomCities();
}

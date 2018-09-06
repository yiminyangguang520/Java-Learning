package com.lee.resttemple.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author litz-a
 */
@Data
@AllArgsConstructor
public class User {
  private String username;
  private String password;
}

package com.lee.resttemple.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author min
 */
@Data
@AllArgsConstructor
public class User {
  private String username;
  private String password;
}

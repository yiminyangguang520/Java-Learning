package com.example.springrediscache.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author litz-a
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private int userId;
  private String username;
  private String password;

}

package com.wanari.customlogin.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author litz-a
 */
public class User {

  @JsonIgnore
  public String id;

  @JsonIgnore
  public String password;

  public String login;
  public List<String> roles = new ArrayList<>();

  public User() {
    this.id = UUID.randomUUID().toString();
  }
}

package com.michaelcgood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author min
 */
@Entity
public class Account {

  @OneToMany(mappedBy = "account")
  private Set<Usernames> usernames = new HashSet<>();

  @Id
  @GeneratedValue
  private Long id;

  @JsonIgnore
  public String password;
  public String username;

  Account() {

  }

  public Account(String name, String password) {
    this.username = name;
    this.password = password;
  }


  public Set<Usernames> getUsernames() {
    return usernames;
  }

  public void setUsernames(Set<Usernames> usernames) {
    this.usernames = usernames;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}

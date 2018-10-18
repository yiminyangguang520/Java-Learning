package com.michaelcgood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author litz-a
 */
@Entity
public class Usernames {

  @JsonIgnore
  @ManyToOne
  private Account account;

  @Id
  @GeneratedValue
  private Long id;

  public String url;
  public String username;

  Usernames() {

  }

  public Usernames(Account account, String url, String username) {
    this.url = url;
    this.account = account;
    this.username = username;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}

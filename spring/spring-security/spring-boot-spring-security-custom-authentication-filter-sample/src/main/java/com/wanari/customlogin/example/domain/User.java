package com.wanari.customlogin.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author bruce
 */
@Getter
@Setter
@Entity(name = "user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User implements Serializable {

  @Id
  @JsonIgnore
  @GeneratedValue(generator = "jpa-uuid")
  private String id;

  @JsonIgnore
  private String password;

  private String login;

  private String roles;

  public User() {
  }

  public User(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public User(String login, String password, String roles) {
    this.login = login;
    this.password = password;
    this.roles = roles;
  }

}

package com.bfwg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author fan.jin
 * @date 2016-11-03
 */

@Entity
@Table(name = "AUTHORITY")
public class Authority implements GrantedAuthority {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "name")
  UserRoleName name;

  @Override
  public String getAuthority() {
    return name.name();
  }

  public void setName(UserRoleName name) {
    this.name = name;
  }

  @JsonIgnore
  public UserRoleName getName() {
    return name;
  }

  @JsonIgnore
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}

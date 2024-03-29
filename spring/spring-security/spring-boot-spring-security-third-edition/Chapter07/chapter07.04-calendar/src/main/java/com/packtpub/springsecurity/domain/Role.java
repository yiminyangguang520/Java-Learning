package com.packtpub.springsecurity.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Mick Knutson
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
  private Set<CalendarUser> users;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<CalendarUser> getUsers() {
    return users;
  }

  public void setUsers(Set<CalendarUser> users) {
    this.users = users;
  }


  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

} // The End...
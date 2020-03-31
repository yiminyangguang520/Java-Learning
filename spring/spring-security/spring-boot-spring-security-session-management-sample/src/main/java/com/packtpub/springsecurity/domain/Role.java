package com.packtpub.springsecurity.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Role)) {
      return false;
    }

    Role role = (Role) o;

    return new EqualsBuilder()
        .append(getId(), role.getId())
        .append(getName(), role.getName())
        .append(getUsers(), role.getUsers())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getId())
        .append(getName())
        .append(getUsers())
        .toHashCode();
  }
}
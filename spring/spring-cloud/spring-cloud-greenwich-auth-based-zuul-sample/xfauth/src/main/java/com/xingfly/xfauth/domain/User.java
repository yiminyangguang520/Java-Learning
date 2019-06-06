package com.xingfly.xfauth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xingfly.xfauth.domain.base.BaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@Entity
@Data
public class User extends BaseEntity implements UserDetails {

  @Id
  @GeneratedValue
  private Long id;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  @Email
  private String email;
  private String imageUrl;


  @JsonIgnore
  @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
  @BatchSize(size = 20)
  private Set<Role> roles = new HashSet<>();

  @Transient
  private Set<GrantedAuthority> authorities = new HashSet<>();


  @Override
  public Set<GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> authorities = new HashSet<>();
    for (Role role : this.roles) {
      for (Authority authority : role.getAuthorities()) {
        authorities.add(new SimpleGrantedAuthority(authority.getValue()));
      }
    }
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

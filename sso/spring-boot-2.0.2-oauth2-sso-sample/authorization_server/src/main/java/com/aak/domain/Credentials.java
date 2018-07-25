package com.aak.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author ahmed
 * @date 21.5.18
 */

@Entity
@Table(name = "credentials")
public class Credentials implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Version
  private Integer version;

  @NotEmpty
  private String name;

  @NotEmpty
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Authority> authorities;

  private boolean enabled;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<Authority> authorities) {
    this.authorities = authorities;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}

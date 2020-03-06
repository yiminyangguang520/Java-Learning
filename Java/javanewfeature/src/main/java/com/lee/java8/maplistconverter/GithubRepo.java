/*
 * GithubRepo.java
 *
 * Created on 2018-03-22, 7:35
 */
package com.lee.java8.maplistconverter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * https://api.github.com/orgs/marcnuri-demo/repos
 *
 * @author Marc Nuri <marc@marcnuri.com>
 * @date 2018-03-22
 */
public class GithubRepo {

  private String name;

  @JsonProperty("full_name")
  private String fullName;

  private String description;

  private Boolean fork;

  @JsonIgnore
  private Integer localVersion;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GithubRepo that = (GithubRepo) o;

    if (!Objects.equals(name, that.name)) {
      return false;
    }
    if (!Objects.equals(fullName, that.fullName)) {
      return false;
    }
    if (!Objects.equals(description, that.description)) {
      return false;
    }
    if (!Objects.equals(fork, that.fork)) {
      return false;
    }
    return Objects.equals(localVersion, that.localVersion);
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (fork != null ? fork.hashCode() : 0);
    result = 31 * result + (localVersion != null ? localVersion.hashCode() : 0);
    return result;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getFork() {
    return fork;
  }

  public void setFork(Boolean fork) {
    this.fork = fork;
  }

  public Integer getLocalVersion() {
    return localVersion;
  }

  public void setLocalVersion(Integer localVersion) {
    this.localVersion = localVersion;
  }

}

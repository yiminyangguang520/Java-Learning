package com.packtpub.springsecurity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.security.Principal;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


/**
 * {@link CalendarUser} is this applications notion of a user. It is good to use your own objects to interact with a user especially in large applications. This ensures
 * that as you evolve your security requirements (update Spring Security, leverage new Spring Security modules, or even swap out security implementations) you can do so
 * easily.
 *
 * @author Rob Winch
 * @author Mick Knutson
 */
public class CalendarUser implements Principal, Serializable {

  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;


  /**
   * Gets the email address for this user. When authenticating against this data directly, this is also used as the username.
   */
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the first name of the user.
   */
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the id for this user. When creating a new user this should be null, otherwise it will be non-null.
   */
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Gets the last name of the user.
   */
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the password for this user. In some instances, this password is not actually used. For example, when an in memory authentication is used the password on the
   * spring security User object is used.
   */
  @JsonIgnore
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  // --- convenience methods ---

  /**
   * Gets the full name in a formatted fashion. Note in a real application a formatter may be more appropriate, but in this application simplicity is more important.
   */
  @Override
  @JsonIgnore
  public String getName() {
    return getEmail();
  }

  // --- override Object ---

  @Override
  public int hashCode() {
    //return HashCodeBuilder.reflectionHashCode(this);
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    //return EqualsBuilder.reflectionEquals(this, obj);
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    CalendarUser other = (CalendarUser) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  private static final long serialVersionUID = 8433999509932007961L;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

} // The End...

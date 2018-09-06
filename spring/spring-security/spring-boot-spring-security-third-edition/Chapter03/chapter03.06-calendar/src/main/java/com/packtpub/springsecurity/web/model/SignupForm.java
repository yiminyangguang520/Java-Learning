package com.packtpub.springsecurity.web.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


public class SignupForm {

  @NotEmpty(message = "First Name is required")
  private String firstName;

  @NotEmpty(message = "Last Name is required")
  private String lastName;

  @Email(message = "Please provide a valid email address")
  @NotEmpty(message = "Email is required")
  private String email;

  @NotEmpty(message = "Password is required")
  private String password;

  /**
   * Gets the email address for this user.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Gets the first name of the user.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Gets the last name of the user.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Gets the password for this user.
   */
  public String getPassword() {
    return password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

}

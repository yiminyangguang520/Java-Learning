package com.memorynotfound.spring.security.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author min
 */
public class PasswordForgotDto {

  @Email
  @NotEmpty
  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}

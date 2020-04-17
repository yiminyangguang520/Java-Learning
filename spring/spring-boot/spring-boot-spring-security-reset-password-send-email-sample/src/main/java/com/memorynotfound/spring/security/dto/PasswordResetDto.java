package com.memorynotfound.spring.security.dto;

import com.memorynotfound.spring.security.constraint.FieldMatch;
import javax.validation.constraints.NotEmpty;

/**
 * @author min
 */
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class PasswordResetDto {

  @NotEmpty
  private String password;

  @NotEmpty
  private String confirmPassword;

  @NotEmpty
  private String token;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}

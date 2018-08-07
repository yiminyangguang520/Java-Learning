package com.example.polls.payload;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author rajeevkumarsingh
 * @date 02/08/17
 */
@Getter
@Setter
public class LoginRequest {

  @NotBlank
  private String usernameOrEmail;

  @NotBlank
  private String password;
}

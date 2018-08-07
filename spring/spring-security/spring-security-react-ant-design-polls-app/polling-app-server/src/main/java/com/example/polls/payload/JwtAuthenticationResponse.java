package com.example.polls.payload;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author rajeevkumarsingh
 * @date 19/08/17
 */
@Getter
@Setter
public class JwtAuthenticationResponse {

  private String accessToken;
  private String tokenType = "Bearer";

  public JwtAuthenticationResponse(String accessToken) {
    this.accessToken = accessToken;
  }
}

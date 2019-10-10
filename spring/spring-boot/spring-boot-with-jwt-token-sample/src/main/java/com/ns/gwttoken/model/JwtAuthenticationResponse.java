package com.ns.gwttoken.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author litz-a
 */
@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {

  private String accessToken;
  private final String tokenType = "Bearer";

}

package com.websystique.springmvc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author min
 */
@Getter
@Setter
@ToString
public class AuthTokenInfo {

  private String access_token;
  private String token_type;
  private String refresh_token;
  private int expires_in;
  private String scope;
}

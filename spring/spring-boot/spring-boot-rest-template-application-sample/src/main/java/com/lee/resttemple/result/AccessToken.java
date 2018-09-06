package com.lee.resttemple.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author litz-a
 */
@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class AccessToken {

  @JsonProperty(value = "access_token")
  private String accessToken;

  @JsonProperty(value = "token_type")
  private String tokenType;

  @JsonProperty(value = "refresh_token")
  private String refreshToken;

  @JsonProperty(value = "expires_in")
  private long expiresIn;

  @JsonProperty(value = "scope")
  private String scope;
}

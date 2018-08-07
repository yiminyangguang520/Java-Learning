package com.bfwg.security.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author fanjin
 * @date 2017-10-09
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationRequest {

  private String username;
  private String password;
}

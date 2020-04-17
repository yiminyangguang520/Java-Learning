package com.ns.gwttoken.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author min
 */
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateRequest {

  private String userName;
  private String password;
}

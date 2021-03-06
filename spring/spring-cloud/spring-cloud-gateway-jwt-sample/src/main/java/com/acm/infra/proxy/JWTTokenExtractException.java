package com.acm.infra.proxy;

import com.auth0.jwt.exceptions.JWTVerificationException;

/**
 * @author min
 */
public class JWTTokenExtractException extends JWTVerificationException {

  public JWTTokenExtractException(String message) {
    super(message);
  }
}

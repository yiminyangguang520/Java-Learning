package org.zerhusen.security.controller;

/**
 * @author litz-a
 */
public class AuthenticationException extends RuntimeException {

  public AuthenticationException(String message, Throwable cause) {
    super(message, cause);
  }
}

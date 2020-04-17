package org.zerhusen.security.controller;

/**
 * @author min
 */
public class AuthenticationException extends RuntimeException {

  public AuthenticationException(String message, Throwable cause) {
    super(message, cause);
  }
}

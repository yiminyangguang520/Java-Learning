package com.michaelcgood.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author litz-a
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 7537022054146700535L;

  public UserNotFoundException(String userId) {
    super("Sorry, we could not find user '" + userId + "'.");
  }
}

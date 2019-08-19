package com.streaming.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author litz-a
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {

  public MyFileNotFoundException(String message) {
    super(message);
  }

  public MyFileNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
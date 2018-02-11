package com.my.blog.website.exception;

/**
 * @author litz-a
 */
public class TipException extends RuntimeException {

  public TipException() {
  }

  public TipException(String message) {
    super(message);
  }

  public TipException(String message, Throwable cause) {
    super(message, cause);
  }

  public TipException(Throwable cause) {
    super(cause);
  }

}

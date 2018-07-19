package com.glodon.sso.core.exception;

/**
 * @author litz-a
 */
public class GcrSsoException extends RuntimeException {

  public GcrSsoException(String msg) {
    super(msg);
  }

  public GcrSsoException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public GcrSsoException(Throwable cause) {
    super(cause);
  }
}

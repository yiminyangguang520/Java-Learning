package net.ameizi.springboot.jwt.sample.system.exception;

public class TokenParserException extends RuntimeException {

  public TokenParserException() {
  }

  public TokenParserException(String message) {
    super(message);
  }

  public TokenParserException(String message, Throwable cause) {
    super(message, cause);
  }

  public TokenParserException(Throwable cause) {
    super(cause);
  }
}

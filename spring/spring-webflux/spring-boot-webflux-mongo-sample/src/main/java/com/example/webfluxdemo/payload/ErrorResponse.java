package com.example.webfluxdemo.payload;

/**
 *
 * @author rajeevkumarsingh
 * @date 22/10/17
 */
public class ErrorResponse {

  private String message;

  public ErrorResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

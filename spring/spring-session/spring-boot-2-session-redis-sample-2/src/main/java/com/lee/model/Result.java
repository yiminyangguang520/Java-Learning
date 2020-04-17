package com.lee.model;

import java.io.Serializable;

/**
 * @author min
 */
public class Result<T> implements Serializable {

  public static final int SUCCESS = 100;
  private int code;
  private String message;
  private T result;
  private long timestamp = System.currentTimeMillis();

  public Result() {
    this.code = SUCCESS;
  }

  public Result(T result) {
    this.code = SUCCESS;
    this.result = result;
  }

  public Result(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public Result(int code, String message, T result) {
    this.code = code;
    this.message = message;
    this.result = result;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "Result{" +
        "code=" + code +
        ", message='" + message + '\'' +
        ", result=" + result +
        ", timestamp=" + timestamp +
        '}';
  }
}

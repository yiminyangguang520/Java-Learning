package com.glodon.sso.core.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author litz-a
 */
@Getter
@Setter
public class SsoResponse<T> implements Serializable {

  public static final int SUCCESS_CODE = 200;
  public static final int FAIL_CODE = 500;

  public static final SsoResponse<String> SUCCESS = new SsoResponse<>(null);
  public static final SsoResponse<String> FAIL = new SsoResponse<>(FAIL_CODE, null);

  private int code;
  private String message;
  private T data;

  public SsoResponse(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public SsoResponse(T data) {
    this.code = SUCCESS_CODE;
    this.data = data;
  }
}

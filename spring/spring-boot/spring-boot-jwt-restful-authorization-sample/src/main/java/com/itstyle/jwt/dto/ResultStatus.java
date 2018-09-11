package com.itstyle.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义请求状态码
 * @author litz-a
 */
@AllArgsConstructor
public enum ResultStatus {

  SUCCESS(200, "成功"),
  USERNAME_OR_PASSWORD_ERROR(-1001, "用户名或密码错误"),
  USER_NOT_FOUND(-1002, "用户不存在"),
  USER_NOT_LOGIN(-1003, "用户未登录"),
  TOKEN_EXPIRE(4001, "签名过期"),
  TOKEN_NOT_PASS(4002, "签名验证不通过"),
  TOKEN_NOT_EXIST(4000, "签名验证不存在");

  /**
   * 返回码
   */

  @Getter
  @Setter
  private int code;

  /**
   * 返回结果描述
   */
  @Getter
  @Setter
  private String message;
}
package com.scienjus.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义请求状态码
 *
 * @author ScienJus
 * @date 2015/7/15.
 */
@AllArgsConstructor
public enum ResultStatus {
  SUCCESS(200, "成功"),
  USERNAME_OR_PASSWORD_ERROR(-1001, "用户名或密码错误"),
  USER_NOT_FOUND(-1002, "用户不存在"),
  USER_NOT_LOGIN(-1003, "用户未登录");

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

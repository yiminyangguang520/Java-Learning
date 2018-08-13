package com.scienjus.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Token的Model类，可以增加字段提高安全性，例如时间戳、url签名
 *
 * @author ScienJus
 * @date 2015/7/31.
 */
@Getter
@Setter
@AllArgsConstructor
public class TokenModel {

  /**
   * 用户id
   */
  private long userId;

  /**
   * 随机生成的uuid
   */
  private String token;
}

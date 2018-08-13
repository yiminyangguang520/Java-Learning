package com.itstyle.jwt.vo;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.Setter;

/**
 * 验证信息
 * @author litz-a
 */
@Getter
@Setter
public class ValidateResult {

  private int errCode;

  private boolean success;

  private Claims claims;
}

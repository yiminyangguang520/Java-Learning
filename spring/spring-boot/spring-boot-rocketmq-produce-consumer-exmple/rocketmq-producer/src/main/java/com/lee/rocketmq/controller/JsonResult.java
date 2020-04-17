package com.lee.rocketmq.controller;

import com.lee.rocketmq.constant.CodeStatus;
import lombok.Data;

/**
 * @author min
 */
@Data
public class JsonResult {

  private String code;

  private String message;

  private Object data;

  public JsonResult(CodeStatus codeStatus) {
    this.code = codeStatus.getCode();
    this.message = codeStatus.getMessage();
  }


  public JsonResult(CodeStatus codeStatus, Object data) {
    this.code = codeStatus.getCode();
    this.message = codeStatus.getMessage();
    this.data = data;
  }

  public JsonResult(String code, String message) {
    this.code = code;
    this.message = message;
  }

}
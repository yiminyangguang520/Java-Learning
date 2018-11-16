package com.lee.rocketmq.dto;

import com.lee.rocketmq.constant.CodeStatus;
import com.lee.rocketmq.constant.SystemCode;
import lombok.NoArgsConstructor;

/**
 * @author litz-a
 */
@NoArgsConstructor
public class OperationResult<T> {

  private Boolean succ = true;

  private CodeStatus errorCode;

  private T entity;

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  private String code;

  private String message;

  public OperationResult(T entity) {
    this.entity = entity;
  }

  public OperationResult(CodeStatus errorCode) {
    this.succ = false;
    this.errorCode = errorCode;
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
  }

  public OperationResult(String errorCode, String errorMessage) {
    this.succ = false;
    this.code = errorCode;
    this.message = errorMessage;
  }

  public OperationResult(CodeStatus errorCode, T entity) {
    if (SystemCode.SUCCESS.getCode().equals(errorCode.getCode())) {
      this.succ = true;
    } else {
      this.succ = false;
    }
    this.errorCode = errorCode;
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
    this.entity = entity;
  }

  public Boolean getSucc() {
    return succ;
  }


  public CodeStatus getErrorCode() {
    return errorCode;
  }

  public T getEntity() {
    return entity;
  }
}

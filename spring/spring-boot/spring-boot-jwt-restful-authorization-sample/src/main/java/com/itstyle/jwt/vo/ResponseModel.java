package com.itstyle.jwt.vo;

/**
 * @author litz-a
 */
public class ResponseModel {

  /**
   * 返回码
   */
  private int code;

  /**
   * 返回结果描述
   */
  private String message;

  /**
   * 返回内容
   */
  private Object content;

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public Object getContent() {
    return content;
  }

  public ResponseModel(int code, String message) {
    this.code = code;
    this.message = message;
    this.content = "";
  }

  public ResponseModel(int code, String message, Object content) {
    this.code = code;
    this.message = message;
    this.content = content;
  }

  public ResponseModel(ResultStatus status) {
    this.code = status.getCode();
    this.message = status.getMessage();
    this.content = "";
  }

  public ResponseModel(ResultStatus status, Object content) {
    this.code = status.getCode();
    this.message = status.getMessage();
    this.content = content;
  }

  public static ResponseModel ok(Object content) {
    return new ResponseModel(ResultStatus.SUCCESS, content);
  }

  public static ResponseModel ok() {
    return new ResponseModel(ResultStatus.SUCCESS);
  }

  public static ResponseModel error(ResultStatus error) {
    return new ResponseModel(error);
  }

  public static ResponseModel error() {
    return new ResponseModel(ResultStatus.USERNAME_OR_PASSWORD_ERROR);
  }
}

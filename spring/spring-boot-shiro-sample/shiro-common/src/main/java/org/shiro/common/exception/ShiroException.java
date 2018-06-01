package org.shiro.common.exception;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午9:35:00
 * 类说明：自定义异常
 */
public class ShiroException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String msg;
  private int code = 500;

  public ShiroException(String msg) {
    super(msg);
    this.msg = msg;
  }

  public ShiroException(String msg, Throwable e) {
    super(msg, e);
    this.msg = msg;
  }

  public ShiroException(String msg, int code) {
    super(msg);
    this.msg = msg;
    this.code = code;
  }

  public ShiroException(String msg, int code, Throwable e) {
    super(msg, e);
    this.msg = msg;
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }


}

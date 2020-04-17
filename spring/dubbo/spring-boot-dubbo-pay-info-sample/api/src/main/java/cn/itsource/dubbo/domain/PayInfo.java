package cn.itsource.dubbo.domain;

import java.io.Serializable;

/**
 * @author min
 */
public class PayInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 支付id
   */
  private Long id;

  /**
   * 字符方式
   */
  private String method;

  /**
   * 支付状态
   */
  private Integer status;

  /**
   * 支付人名称
   */
  private String userName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

}

package com.us.example.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author min
 */
@Getter
@Setter
public class Permission {

  private int id;
  /**
   * 权限名称
   */
  private String name;

  /**
   * 权限描述
   */
  private String descritpion;

  /**
   * 授权链接
   */
  private String url;

  /**
   * 父节点id
   */
  private int pid;

  /**
   * 请求方法
   */
  private String method;
}

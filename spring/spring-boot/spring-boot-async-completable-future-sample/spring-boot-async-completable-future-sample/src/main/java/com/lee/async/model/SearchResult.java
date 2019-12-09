package com.lee.async.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author bruce
 * @date 2019/10/22
 * @description 搜索结构
 */
@Data
@ToString
public class SearchResult {

  /**
   * 返回码
   */
  private int code = 200;

  /**
   * 返回说明
   */
  private String message = "OK";

  /**
   * 返回结果
   */
  private Object data;

  public SearchResult(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public SearchResult(Object object) {
    this.data = object;
  }
}

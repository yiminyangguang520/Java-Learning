package com.imooc.common.enums;

/**
 * @author litz-a
 * @Description: 是否枚举
 */
public enum YesOrNo {

  /**
   * 有错误
   */
  YES(1),

  /**
   * 无错误
   */
  NO(0);

  public final int value;

  YesOrNo(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}

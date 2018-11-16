package com.wolf.mongodbit.customenum;

import lombok.Getter;

/**
 * 黑名单枚举处理
 * @author litz-a
 */
@Getter
public enum BlackEnum {

  EXIST("1111", "已经被列入黑名单！"),
  NOTSTATUS("2222", "黑名单状态不符合！"),
  NOTTYPE("3333", "黑名单类型不符合！"),
  NOTUSERNAME("4444", "黑名单用户名不能为空！");

  private String code;

  private String msg;

  BlackEnum(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}

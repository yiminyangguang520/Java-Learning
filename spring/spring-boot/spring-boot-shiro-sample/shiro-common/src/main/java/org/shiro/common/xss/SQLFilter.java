package org.shiro.common.xss;

import org.apache.commons.lang.StringUtils;
import org.shiro.common.exception.ShiroException;

/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午9:48:16
 * 类说明： SQL过滤
 */
public class SQLFilter {

  /**
   * SQL注入过滤
   *
   * @param str 待验证的字符串
   */
  public static String sqlInject(String str) {
    if (StringUtils.isBlank(str)) {
      return null;
    }
    //去掉'|"|;|\字符
    str = StringUtils.replace(str, "'", "");
    str = StringUtils.replace(str, "\"", "");
    str = StringUtils.replace(str, ";", "");
    str = StringUtils.replace(str, "\\", "");

    //转换成小写
    str = str.toLowerCase();

    //非法字符
    String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

    //判断是否包含非法字符
    for (String keyword : keywords) {
      if (str.indexOf(keyword) != -1) {
        throw new ShiroException("包含非法字符");
      }
    }

    return str;
  }
}

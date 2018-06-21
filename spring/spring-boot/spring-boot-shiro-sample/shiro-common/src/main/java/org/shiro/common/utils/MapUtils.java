package org.shiro.common.utils;

import java.util.HashMap;

/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午9:40:18
 * 类说明：Map工具类
 */
public class MapUtils extends HashMap<String, Object> {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Override
  public MapUtils put(String key, Object value) {
    super.put(key, value);
    return this;
  }
}

package org.shiro.common.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午9:38:46
 * 类说明：返回数据
 */
public class Result extends HashMap<String, Object> {

  private static final long serialVersionUID = 1L;

  public Result() {
    put("code", 0);
    put("msg", "success");
  }

  public static Result error() {
    return error(500, "未知异常，请联系管理员");
  }

  public static Result error(String msg) {
    return error(500, msg);
  }

  public static Result error(int code, String msg) {
    Result r = new Result();
    r.put("code", code);
    r.put("msg", msg);
    return r;
  }

  public static Result ok(String msg) {
    Result r = new Result();
    r.put("msg", msg);
    return r;
  }

  public static Result ok(Map<String, Object> map) {
    Result r = new Result();
    r.putAll(map);
    return r;
  }

  public static Result ok() {
    return new Result();
  }

  @Override
  public Result put(String key, Object value) {
    super.put(key, value);
    return this;
  }
}

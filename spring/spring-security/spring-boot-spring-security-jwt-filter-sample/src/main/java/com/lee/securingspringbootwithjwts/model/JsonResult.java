package com.lee.securingspringbootwithjwts.model;

import org.json.JSONObject;

/**
 * @author min
 */
public class JsonResult {

  public static String fillResultString(Integer status, String message, Object result) {
    JSONObject jsonObject = new JSONObject() {{
      put("status", status);
      put("message", message);
      put("result", result);
    }};

    return jsonObject.toString();
  }
}

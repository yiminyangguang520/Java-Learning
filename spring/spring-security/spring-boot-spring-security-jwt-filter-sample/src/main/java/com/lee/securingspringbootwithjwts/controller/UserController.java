package com.lee.securingspringbootwithjwts.controller;

import com.lee.securingspringbootwithjwts.model.JsonResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class UserController {

  @RequestMapping("/")
  public Map<String, String> index() {
    Map<String, String> map = new HashMap<>(1);
    map.put("content", "hello freewolf~");
    return map;
  }

  /**
   * 路由映射到/users
   */
  @RequestMapping(value = "/users", produces = "application/json;charset=UTF-8")
  public String usersList() {

    ArrayList<String> users = new ArrayList<String>() {{
      add("freewolf");
      add("tom");
      add("jerry");
    }};

    return JsonResult.fillResultString(0, "", users);
  }

  @RequestMapping(value = "/hello", produces = "application/json;charset=UTF-8")
  public String hello() {
    ArrayList<String> users = new ArrayList<String>() {{
      add("hello");
    }};
    return JsonResult.fillResultString(0, "", users);
  }

  @RequestMapping(value = "/world", produces = "application/json;charset=UTF-8")
  public String world() {
    ArrayList<String> users = new ArrayList<String>() {{
      add("world");
    }};
    return JsonResult.fillResultString(0, "", users);
  }
}

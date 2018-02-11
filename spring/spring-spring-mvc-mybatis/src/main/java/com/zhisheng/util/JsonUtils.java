package com.zhisheng.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhisheng.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * @author litz-a
 * Created by 10412 on 2017/8/09.
 */
public class JsonUtils {

  public static String objectToJson(Object data) {
    return JSONArray.toJSONString(data);
  }

  public static User jsonObjectToUser(String userString) {
    return JSONObject.parseObject(userString.toString(), User.class);
  }

  public static List<User> objectToUser(JSONArray array) {
    List<User> userList = new ArrayList<>();

    array.forEach(e -> {
      User user = JSONObject.parseObject(e.toString(), User.class);
      userList.add(user);
    });

    return userList;
  }
}

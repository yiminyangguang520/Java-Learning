package com.zhisheng.controller;

import com.alibaba.fastjson.JSON;
import com.zhisheng.model.User;
import com.zhisheng.service.IJedisClientService;
import com.zhisheng.service.IUserService;
import com.zhisheng.util.JsonUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author min
 * Created by 10412 on 2017/8/10.
 */
@Controller
@RequestMapping("user")
public class UserController {

  private Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private IUserService userService;

  @Autowired
  private IJedisClientService jedisClientService;

  /**
   * 查询 topN 的数据，若redis中没有则从数据库查询出来后将数据放到redis中，
   * 再次查询的时候就直接从redis中查询出来
   */
  @RequestMapping("queryTopN")
  public String queryTopN() {
    List<User> userList = null;
    try {
      //拿出redis的排行榜结果
      Set<String> set = jedisClientService.zgetAll("Toptest", 0, 20);
      System.out.println("set : " + set.toString());
      //遍历
      Iterator<String> iterator = set.iterator();
      if (set.size() > 0) {
        System.out.println("有缓存了");
        userList = new ArrayList<>();
        while (iterator.hasNext()) {
          //把一个个对象用fastjson格式转换成jsonobject
          User user = JsonUtils.jsonObjectToUser(iterator.next());
          System.out.println("user : " + user.toString());
          userList.add(user);
        }
        return JSON.toJSONString(userList);
      } else {
        System.out.println("还没有查询过 Toptest");
        //没有查询就查询一次，然后再将结果放到 redis
        userList = userService.queryTopN();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      jedisClientService.zaddList("Toptest", userList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return JSON.toJSONString(userList);
  }

}

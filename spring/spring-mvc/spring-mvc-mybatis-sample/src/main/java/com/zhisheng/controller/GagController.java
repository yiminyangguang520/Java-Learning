package com.zhisheng.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhisheng.model.Gag;
import com.zhisheng.service.IGagService;
import com.zhisheng.service.IJedisClientService;
import com.zhisheng.util.JsonUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author min
 * Created by 10412 on 2017/8/9.
 */
@Controller
@RequestMapping("gag")
public class GagController {

  @Autowired
  private IGagService gagService;

  @Autowired
  private IJedisClientService jedisClientService;

  //再写个业务方法
  //基本思路：有没有查过禁言表，有查过就往redis拿，null的话就去数据库拿，拿完再把数据set进缓存！！
  //浏览器地址栏：http://localhost:8888/gag/testRedis/1   ，注意要开启 redis
  @RequestMapping("/testRedis/{id}")
  public String testRedis(@PathVariable("id") long id) {
    List<Gag> gagList = null;
    try {
      String s = jedisClientService.hget("禁言表", id + "");
      if (s != null) {
        System.out.println("有缓存了");
        //字符串转换成list
        JSONArray jsonArray = JSONArray.parseArray(s);
        gagList = (List) jsonArray;
      } else {
        System.out.println("禁言表在缓存中不存在");
        gagList = gagService.selectGagByUserId(id);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      String s = JsonUtils.objectToJson(gagList);
      jedisClientService.hset("禁言表", id + "", s);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return JSON.toJSONString(gagList);
  }
}

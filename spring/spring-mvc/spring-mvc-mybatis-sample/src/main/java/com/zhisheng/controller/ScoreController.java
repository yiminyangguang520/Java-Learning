package com.zhisheng.controller;

import com.alibaba.fastjson.JSON;
import com.zhisheng.model.Score;
import com.zhisheng.model.User;
import com.zhisheng.service.IScoreService;
import com.zhisheng.service.IUserService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author min
 * Created by 10412 on 2017/8/9.
 */
@Controller
@RequestMapping("score")
public class ScoreController {

  private Logger logger = LoggerFactory.getLogger(ScoreController.class);

  @Autowired
  private IScoreService scoreService;

  @Autowired
  private IUserService userService;


  /**
   * 插入积分记录
   */
  @RequestMapping("/insert")
  public String insert() {
    Score score = new Score();
    score.setChangeType("玩蛇皮");
    score.setScore(20);
    score.setCreate_time(new Date());
    scoreService.insertScore(score);
    return "score";
  }

  /**
   * 更改积分
   */
  @RequestMapping("updateScore/{id}/{scoreCount}")
  public String updateScore(@PathVariable("id") long id, @PathVariable("scoreCount") Integer scoreCount) {
    User user = userService.queryById(id);
    scoreService.updateScore(user, scoreCount);
    return JSON.toJSONString(user);
  }
}

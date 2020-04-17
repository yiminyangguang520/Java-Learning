package com.room.controller;

import com.room.mq.sender.QueueSender;
import com.room.mq.sender.TopicSender;
import com.room.service.IWordService;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author min
 * Created by Doublestar on 2017/12/26 23:38).
 */
@Controller
@RequestMapping("/home/mq")
public class MqController {

  @Resource
  private QueueSender queueSender;

  @Resource
  private TopicSender topicSender;

  @Resource
  private IWordService iWordService;

  @ResponseBody
  @RequestMapping("adminSender")
  public String adminSender(@RequestParam("message") String message) {
    String opt;
    try {
      topicSender.send("yunsle.admin", message);
      opt = "suc";
    } catch (Exception e) {
      opt = e.getCause().toString();
    }
    return opt;
  }

  @ResponseBody
  @RequestMapping("queueSender")
  public String queueSender(@RequestParam("message") String message) {
    String opt;
    try {
      queueSender.send("yunsle.queue", message);
      opt = "suc";
    } catch (Exception e) {
      opt = e.getCause().toString();
    }
    return opt;
  }


  @ResponseBody
  @RequestMapping("topicSender")
  public String topicSender(@RequestParam("message") String message, HttpSession session) {
    String opt;
    //进行敏感词检测
    //未通过检测
    if (!iWordService.filterWords(message)) {
      //返回错误信息
      opt = "danger";
    } else {
      //通过检测
      try {
        //聊天记录显示用户名
        topicSender.send("yunsle.topic",
            session.getAttribute("name") + "说:" + message);
        opt = "suc";
      } catch (Exception e) {
        opt = e.getCause().toString();
      }
    }
    return opt;
  }


  @ResponseBody
  @RequestMapping(value = "dmSender", method = RequestMethod.POST)
  public String dmSender(@RequestParam("message") String message) {
    String opt;
    //进行敏感词检测
    if (!iWordService.filterWords(message)) {
      //未通过检测,返回错误信息
      opt = "danger";
    } else {
      //通过检测
      try {
        topicSender.send("yunsle.danmu", message);
        opt = "success";
      } catch (Exception e) {
        opt = e.getCause().toString();
      }
    }
    return opt;
  }

}

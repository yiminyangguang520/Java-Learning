package com.lee.rocketmq.controller;

import com.lee.rocketmq.dto.OperationResult;
import com.lee.rocketmq.service.RocketMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class RocketMQController extends BaseController {

  @Autowired
  private RocketMQService rocketMQService;

  @RequestMapping(value = "/send")
  private JsonResult sendMessage() {

    OperationResult<Boolean> result = rocketMQService.sendMessage();

    if (result.getSucc()) {
      return success(result.getEntity());
    }

    return error(result.getCode(), result.getMessage());
  }
}
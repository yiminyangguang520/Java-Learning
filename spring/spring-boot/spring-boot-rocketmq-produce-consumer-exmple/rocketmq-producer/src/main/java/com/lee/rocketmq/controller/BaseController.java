package com.lee.rocketmq.controller;

import com.lee.rocketmq.constant.CodeStatus;
import com.lee.rocketmq.constant.SystemCode;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求基类
 * @author litz-a
 */
@RestController
public class BaseController {

  protected JsonResult success(Object data) {
    return new JsonResult(SystemCode.SUCCESS, data);
  }

  protected JsonResult error(CodeStatus codeStatus) {
    return new JsonResult(codeStatus, null);
  }

  protected JsonResult error(String code, String message) {
    return new JsonResult(code, message);
  }

  protected JsonResult response(CodeStatus codeStatus, Object data) {
    return new JsonResult(codeStatus, data);
  }

}
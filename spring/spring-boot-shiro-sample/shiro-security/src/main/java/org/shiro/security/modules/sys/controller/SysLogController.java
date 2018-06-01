package org.shiro.security.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.shiro.common.utils.PageUtils;
import org.shiro.common.utils.Result;
import org.shiro.security.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午11:13:03
 * 类说明：系统日志
 */
@RestController
@RequestMapping("/sys/log")
@Api(value = "系统日志")
public class SysLogController {

  @Autowired
  private SysLogService sysLogService;

  /**
   * 列表
   */
  @ResponseBody
  @GetMapping("/list")
  @RequiresPermissions("sys:log:list")
  @ApiOperation(value = "列表", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result list(@RequestParam Map<String, Object> params) {
    PageUtils page = sysLogService.queryPage(params);

    return Result.ok().put("page", page);
  }

}

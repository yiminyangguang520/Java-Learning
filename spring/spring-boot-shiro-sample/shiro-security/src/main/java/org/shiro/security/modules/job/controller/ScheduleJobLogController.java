package org.shiro.security.modules.job.controller;

import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.shiro.common.utils.PageUtils;
import org.shiro.common.utils.Result;
import org.shiro.security.modules.job.entity.ScheduleJobLogEntity;
import org.shiro.security.modules.job.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:26:59
 * 类说明：定时任务日志
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {

  @Autowired
  private ScheduleJobLogService scheduleJobLogService;

  /**
   * 定时任务日志列表
   */
  @RequestMapping("/list")
  @RequiresPermissions("sys:schedule:log")
  public Result list(@RequestParam Map<String, Object> params) {
    PageUtils page = scheduleJobLogService.queryPage(params);

    return Result.ok().put("page", page);
  }

  /**
   * 定时任务日志信息
   */
  @RequestMapping("/info/{logId}")
  public Result info(@PathVariable("logId") Long logId) {
    ScheduleJobLogEntity log = scheduleJobLogService.selectById(logId);

    return Result.ok().put("log", log);
  }
}

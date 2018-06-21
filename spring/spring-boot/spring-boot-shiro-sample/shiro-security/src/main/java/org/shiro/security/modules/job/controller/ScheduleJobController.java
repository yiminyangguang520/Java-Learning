package org.shiro.security.modules.job.controller;

import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.shiro.common.utils.PageUtils;
import org.shiro.common.utils.Result;
import org.shiro.common.validator.ValidatorUtils;
import org.shiro.security.common.annotation.SysLog;
import org.shiro.security.modules.job.entity.ScheduleJobEntity;
import org.shiro.security.modules.job.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:04:30
 * 类说明：定时任务
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {

  @Autowired
  private ScheduleJobService scheduleJobService;

  /**
   * 定时任务列表
   */
  @RequestMapping("/list")
  @RequiresPermissions("sys:schedule:list")
  public Result list(@RequestParam Map<String, Object> params) {
    PageUtils page = scheduleJobService.queryPage(params);

    return Result.ok().put("page", page);
  }

  /**
   * 定时任务信息
   */
  @RequestMapping("/info/{jobId}")
  @RequiresPermissions("sys:schedule:info")
  public Result info(@PathVariable("jobId") Long jobId) {
    ScheduleJobEntity schedule = scheduleJobService.selectById(jobId);

    return Result.ok().put("schedule", schedule);
  }

  /**
   * 保存定时任务
   */
  @SysLog("保存定时任务")
  @RequestMapping("/save")
  @RequiresPermissions("sys:schedule:save")
  public Result save(@RequestBody ScheduleJobEntity scheduleJob) {
    ValidatorUtils.validateEntity(scheduleJob);

    scheduleJobService.save(scheduleJob);

    return Result.ok();
  }

  /**
   * 修改定时任务
   */
  @SysLog("修改定时任务")
  @RequestMapping("/update")
  @RequiresPermissions("sys:schedule:update")
  public Result update(@RequestBody ScheduleJobEntity scheduleJob) {
    ValidatorUtils.validateEntity(scheduleJob);

    scheduleJobService.update(scheduleJob);

    return Result.ok();
  }

  /**
   * 删除定时任务
   */
  @SysLog("删除定时任务")
  @RequestMapping("/delete")
  @RequiresPermissions("sys:schedule:delete")
  public Result delete(@RequestBody Long[] jobIds) {
    scheduleJobService.deleteBatch(jobIds);

    return Result.ok();
  }

  /**
   * 立即执行任务
   */
  @SysLog("立即执行任务")
  @RequestMapping("/run")
  @RequiresPermissions("sys:schedule:run")
  public Result run(@RequestBody Long[] jobIds) {
    scheduleJobService.run(jobIds);

    return Result.ok();
  }

  /**
   * 暂停定时任务
   */
  @SysLog("暂停定时任务")
  @RequestMapping("/pause")
  @RequiresPermissions("sys:schedule:pause")
  public Result pause(@RequestBody Long[] jobIds) {
    scheduleJobService.pause(jobIds);

    return Result.ok();
  }

  /**
   * 恢复定时任务
   */
  @SysLog("恢复定时任务")
  @RequestMapping("/resume")
  @RequiresPermissions("sys:schedule:resume")
  public Result resume(@RequestBody Long[] jobIds) {
    scheduleJobService.resume(jobIds);

    return Result.ok();
  }

}

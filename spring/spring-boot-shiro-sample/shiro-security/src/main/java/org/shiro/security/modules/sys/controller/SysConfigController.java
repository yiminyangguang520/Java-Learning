package org.shiro.security.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.shiro.common.utils.PageUtils;
import org.shiro.common.utils.Result;
import org.shiro.common.validator.ValidatorUtils;
import org.shiro.security.common.annotation.SysLog;
import org.shiro.security.modules.sys.entity.SysConfigEntity;
import org.shiro.security.modules.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午11:15:47 类说明：系统配置信息
 */
@RestController
@RequestMapping("/sys/config")
@Api(value = "系统配置信息")
public class SysConfigController extends AbstractController {

  @Autowired
  private SysConfigService sysConfigService;

  /**
   * 所有配置列表
   */
  @GetMapping("/list")
  @RequiresPermissions("sys:config:list")
  @ApiOperation(value = "所有配置列表", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result list(@RequestParam Map<String, Object> params) {
    PageUtils page = sysConfigService.queryPage(params);

    return Result.ok().put("page", page);
  }

  /**
   * 配置信息
   */
  @GetMapping("/info/{id}")
  @RequiresPermissions("sys:config:info")
  @ApiOperation(value = "详细信息", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result info(@PathVariable("id") Long id) {
    SysConfigEntity config = sysConfigService.selectById(id);

    return Result.ok().put("config", config);
  }

  /**
   * 保存配置
   */
  @SysLog("保存配置")
  @PostMapping("/save")
  @RequiresPermissions("sys:config:save")
  @ApiOperation(value = "保存配置", httpMethod = "POST", produces = "application/json", response = Result.class)
  public Result save(@RequestBody SysConfigEntity config) {
    ValidatorUtils.validateEntity(config);
    sysConfigService.save(config);
    return Result.ok();
  }

  /**
   * 修改配置
   */
  @SysLog("修改配置")
  @PutMapping("/update")
  @RequiresPermissions("sys:config:update")
  @ApiOperation(value = "修改配置", httpMethod = "PUT", produces = "application/json", response = Result.class)
  public Result update(@RequestBody SysConfigEntity config) {
    ValidatorUtils.validateEntity(config);

    sysConfigService.update(config);

    return Result.ok();
  }

  /**
   * 删除配置
   */
  @SysLog("删除配置")
  @DeleteMapping("/delete")
  @RequiresPermissions("sys:config:delete")
  @ApiOperation(value = "删除配置", httpMethod = "DELETE", produces = "application/json", response = Result.class)
  public Result delete(@RequestBody Long[] ids) {
    sysConfigService.deleteBatch(ids);

    return Result.ok();
  }

}

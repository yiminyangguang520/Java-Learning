package org.shiro.security.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.shiro.common.utils.PageUtils;
import org.shiro.common.utils.Result;
import org.shiro.common.validator.ValidatorUtils;
import org.shiro.security.modules.sys.entity.SysDictEntity;
import org.shiro.security.modules.sys.service.SysDictService;
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
 * @version 创建时间：2018年4月25日 上午11:13:47
 * 类说明：数据字典
 */
@RestController
@RequestMapping("sys/dict")
@Api(value = "数据字典")
public class SysDictController {

  @Autowired
  private SysDictService sysDictService;

  /**
   * 列表
   */
  @GetMapping("/list")
  @RequiresPermissions("sys:dict:list")
  @ApiOperation(value = "列表", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result list(@RequestParam Map<String, Object> params) {
    PageUtils page = sysDictService.queryPage(params);
    return Result.ok().put("page", page);
  }


  /**
   * 信息
   */
  @GetMapping("/info/{id}")
  @RequiresPermissions("sys:dict:info")
  @ApiOperation(value = "信息", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result info(@PathVariable("id") Long id) {
    SysDictEntity dict = sysDictService.selectById(id);

    return Result.ok().put("dict", dict);
  }

  /**
   * 保存
   */
  @PostMapping("/save")
  @RequiresPermissions("sys:dict:save")
  @ApiOperation(value = "保存", httpMethod = "POST", produces = "application/json", response = Result.class)
  public Result save(@RequestBody SysDictEntity dict) {
    //校验类型
    ValidatorUtils.validateEntity(dict);

    sysDictService.insert(dict);

    return Result.ok();
  }

  /**
   * 修改
   */
  @PutMapping("/update")
  @RequiresPermissions("sys:dict:update")
  @ApiOperation(value = "修改", httpMethod = "PUT", produces = "application/json", response = Result.class)
  public Result update(@RequestBody SysDictEntity dict) {
    //校验类型
    ValidatorUtils.validateEntity(dict);

    sysDictService.updateById(dict);

    return Result.ok();
  }

  /**
   * 删除
   */
  @DeleteMapping("/delete")
  @RequiresPermissions("sys:dict:delete")
  @ApiOperation(value = "删除", httpMethod = "DELETE", produces = "application/json", response = Result.class)
  public Result delete(@RequestBody Long[] ids) {
    sysDictService.deleteBatchIds(Arrays.asList(ids));

    return Result.ok();
  }

}

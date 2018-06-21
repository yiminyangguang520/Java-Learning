package org.shiro.security.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.shiro.common.utils.PageUtils;
import org.shiro.common.utils.Result;
import org.shiro.common.validator.Assert;
import org.shiro.common.validator.ValidatorUtils;
import org.shiro.common.validator.group.AddGroup;
import org.shiro.common.validator.group.UpdateGroup;
import org.shiro.security.common.annotation.SysLog;
import org.shiro.security.common.utils.Constant;
import org.shiro.security.modules.sys.entity.SysUserEntity;
import org.shiro.security.modules.sys.service.SysUserRoleService;
import org.shiro.security.modules.sys.service.SysUserService;
import org.shiro.security.modules.sys.shiro.ShiroUtils;
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
 * @version 创建时间：2018年4月25日 上午11:06:29 类说明：系统用户
 */
@RestController
@RequestMapping("/sys/user")
@Api(value = "系统用户")
public class SysUserController extends AbstractController {

  @Autowired
  private SysUserService sysUserService;
  @Autowired
  private SysUserRoleService sysUserRoleService;

  /**
   * 所有用户列表
   */
  @GetMapping("/list")
  @RequiresPermissions("sys:user:list")
  @ApiOperation(value = "所有用户列表", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result list(@RequestParam Map<String, Object> params) {
    PageUtils page = sysUserService.queryPage(params);

    return Result.ok().put("page", page);
  }

  /**
   * 获取登录的用户信息
   */
  @GetMapping("/info")
  @ApiOperation(value = "获取登录的用户信息", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result info() {
    if (getUser() == null) {
      return Result.error(Constant.NOLONIN);
    } else {
      return Result.ok().put("user", getUser());
    }
  }

  /**
   * 修改登录用户密码
   */
  @SysLog("修改密码")
  @PutMapping("/password")
  @RequiresPermissions("sys:user:password")
  @ApiOperation(value = "修改登录用户密码", httpMethod = "PUT", produces = "application/json", response = Result.class)
  public Result password(String password, String newPassword, Long userId) {
    Assert.isBlank(newPassword, "新密码不为能空");
    SysUserEntity user = sysUserService.selectById(userId);
    // 原密码
    password = ShiroUtils.sha256(password, user.getSalt());
    // 新密码
    newPassword = ShiroUtils.sha256(newPassword, user.getSalt());
    // 更新密码
    boolean flag = sysUserService.updatePassword(userId, password, newPassword);
    if (!flag) {
      return Result.error("原密码不正确");
    }
    return Result.ok();
  }

  /**
   * 用户信息
   */
  @GetMapping("/info/{userId}")
  @RequiresPermissions("sys:user:info")
  @ApiOperation(value = "用户信息", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result info(@PathVariable("userId") Long userId) {
    SysUserEntity user = sysUserService.selectById(userId);
    // 获取用户所属的角色列表
    List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
    user.setRoleIdList(roleIdList);
    return Result.ok().put("user", user);
  }

  /**
   * 保存用户
   */
  @SysLog("保存用户")
  @PostMapping("/save")
  @RequiresPermissions("sys:user:save")
  @ApiOperation(value = "保存用户", httpMethod = "POST", produces = "application/json", response = Result.class)
  public Result save(@RequestBody SysUserEntity user) {
    ValidatorUtils.validateEntity(user, AddGroup.class);
    sysUserService.save(user);
    return Result.ok();
  }

  /**
   * 修改用户
   */
  @SysLog("修改用户")
  @PutMapping("/update")
  @RequiresPermissions("sys:user:update")
  @ApiOperation(value = "修改用户", httpMethod = "PUT", produces = "application/json", response = Result.class)
  public Result update(@RequestBody SysUserEntity user) {
    ValidatorUtils.validateEntity(user, UpdateGroup.class);

    sysUserService.update(user);

    return Result.ok();
  }

  /**
   * 删除用户
   */
  @SysLog("删除用户")
  @DeleteMapping("/delete")
  @RequiresPermissions("sys:user:delete")
  @ApiOperation(value = "删除用户", httpMethod = "DELETE", produces = "application/json", response = Result.class)
  public Result delete(@RequestBody Long[] userIds) {
    if (ArrayUtils.contains(userIds, 1L)) {
      return Result.error("系统管理员不能删除");
    }

    if (ArrayUtils.contains(userIds, getUserId())) {
      return Result.error("当前用户不能删除");
    }

    sysUserService.deleteBatchIds(Arrays.asList(userIds));

    return Result.ok();
  }
}

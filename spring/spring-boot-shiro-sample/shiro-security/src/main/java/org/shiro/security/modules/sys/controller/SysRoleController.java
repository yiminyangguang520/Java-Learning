package org.shiro.security.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.shiro.common.utils.PageUtils;
import org.shiro.common.utils.Result;
import org.shiro.common.validator.ValidatorUtils;
import org.shiro.security.common.annotation.SysLog;
import org.shiro.security.modules.sys.entity.SysRoleEntity;
import org.shiro.security.modules.sys.service.SysRoleDeptService;
import org.shiro.security.modules.sys.service.SysRoleMenuService;
import org.shiro.security.modules.sys.service.SysRoleService;
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
 * @version 创建时间：2018年4月25日 上午11:08:34
 * 类说明：角色管理
 */
@RestController
@RequestMapping("/sys/role")
@Api(value = "角色管理")
public class SysRoleController extends AbstractController {

  @Autowired
  private SysRoleService sysRoleService;
  @Autowired
  private SysRoleMenuService sysRoleMenuService;
  @Autowired
  private SysRoleDeptService sysRoleDeptService;

  /**
   * 角色列表
   */
  @GetMapping("/list")
  @RequiresPermissions("sys:role:list")
  @ApiOperation(value = "角色列表", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result list(@RequestParam Map<String, Object> params) {
    PageUtils page = sysRoleService.queryPage(params);
    return Result.ok().put("page", page);
  }

  /**
   * 角色列表
   */
  @GetMapping("/select")
  @RequiresPermissions("sys:role:select")
  @ApiOperation(value = "角色列表", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result select() {
    List<SysRoleEntity> list = sysRoleService.selectList(null);
    return Result.ok().put("list", list);
  }

  /**
   * 角色信息
   */
  @GetMapping("/info/{roleId}")
//	@RequiresPermissions("sys:role:info")
  @ApiOperation(value = "角色信息", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result info(@PathVariable("roleId") Long roleId) {
    SysRoleEntity role = sysRoleService.selectById(roleId);
    //查询角色对应的菜单
    List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
    role.setMenuIdList(menuIdList);
    //查询角色对应的部门
    List<Long> deptIdList = sysRoleDeptService.queryDeptIdList(new Long[]{roleId});
    role.setDeptIdList(deptIdList);
    return Result.ok().put("role", role);
  }

  /**
   * 保存角色
   */
  @SysLog("保存角色")
  @PostMapping("/save")
  @RequiresPermissions("sys:role:save")
  @ApiOperation(value = "保存角色", httpMethod = "POST", produces = "application/json", response = Result.class)
  public Result save(@RequestBody SysRoleEntity role) {
    ValidatorUtils.validateEntity(role);

    sysRoleService.save(role);

    return Result.ok();
  }

  /**
   * 修改角色
   */
  @SysLog("修改角色")
  @PutMapping("/update")
  @RequiresPermissions("sys:role:update")
  @ApiOperation(value = "修改角色", httpMethod = "PUT", produces = "application/json", response = Result.class)
  public Result update(@RequestBody SysRoleEntity role) {
    ValidatorUtils.validateEntity(role);

    sysRoleService.update(role);

    return Result.ok();
  }

  /**
   * 删除角色
   */
  @SysLog("删除角色")
  @DeleteMapping("/delete")
  @RequiresPermissions("sys:role:delete")
  @ApiOperation(value = "删除角色", httpMethod = "DELETE", produces = "application/json", response = Result.class)
  public Result delete(@RequestBody Long[] roleIds) {
    sysRoleService.deleteBatch(roleIds);

    return Result.ok();
  }
}

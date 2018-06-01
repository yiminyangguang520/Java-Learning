package org.shiro.security.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.shiro.common.exception.ShiroException;
import org.shiro.common.utils.Result;
import org.shiro.security.common.annotation.SysLog;
import org.shiro.security.common.utils.Constant;
import org.shiro.security.modules.sys.entity.SysMenuEntity;
import org.shiro.security.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午11:10:05
 * 类说明：系统菜单
 */
@RestController
@RequestMapping("/sys/menu")
@Api(value = "系统菜单")
public class SysMenuController extends AbstractController {

  @Autowired
  private SysMenuService sysMenuService;

  /**
   * 导航菜单
   */
  @GetMapping("/nav")
  @ApiOperation(value = "导航菜单", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result nav() {
    try {
      List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
      return Result.ok().put("menuList", menuList);
    } catch (Exception e) {
      return Result.error(Constant.NOLONIN);
    }

  }

  /**
   * 所有菜单列表
   */
  @GetMapping("/list")
  @RequiresPermissions("sys:menu:list")
  @ApiOperation(value = "所有菜单列表", httpMethod = "GET", produces = "application/json", response = List.class)
  public List<SysMenuEntity> list() {
    List<SysMenuEntity> menuList = sysMenuService.selectList(null);
    for (SysMenuEntity sysMenuEntity : menuList) {
      SysMenuEntity parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
      if (parentMenuEntity != null) {
        sysMenuEntity.setParentName(parentMenuEntity.getName());
      }
    }

    return menuList;
  }

  /**
   * 选择菜单(添加、修改菜单)
   */
  @GetMapping("/select")
  @RequiresPermissions("sys:menu:select")
  @ApiOperation(value = "选择菜单(添加、修改菜单)", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result select() {
    //查询列表数据
    List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
    //添加顶级菜单
    SysMenuEntity root = new SysMenuEntity();
    root.setMenuId(0L);
    root.setName("一级菜单");
    root.setParentId(-1L);
    root.setOpen(true);
    menuList.add(root);
    return Result.ok().put("menuList", menuList);
  }

  /**
   * 菜单信息
   */
  @GetMapping("/info/{menuId}")
  @RequiresPermissions("sys:menu:info")
  @ApiOperation(value = "菜单信息", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result info(@PathVariable("menuId") Long menuId) {
    SysMenuEntity menu = sysMenuService.selectById(menuId);
    return Result.ok().put("menu", menu);
  }

  /**
   * 保存
   */
  @SysLog("保存菜单")
  @PostMapping("/save")
  @RequiresPermissions("sys:menu:save")
  @ApiOperation(value = "保存菜单", httpMethod = "POST", produces = "application/json", response = Result.class)
  public Result save(@RequestBody SysMenuEntity menu) {
    //数据校验
    verifyForm(menu);

    sysMenuService.insert(menu);

    return Result.ok();
  }

  /**
   * 修改
   */
  @SysLog("修改菜单")
  @PutMapping("/update")
  @RequiresPermissions("sys:menu:update")
  @ApiOperation(value = "修改菜单", httpMethod = "PUT", produces = "application/json", response = Result.class)
  public Result update(@RequestBody SysMenuEntity menu) {
    //数据校验
    verifyForm(menu);

    sysMenuService.updateById(menu);

    return Result.ok();
  }

  /**
   * 删除
   */
  @SysLog("删除菜单")
  @DeleteMapping("/delete")
  @RequiresPermissions("sys:menu:delete")
  @ApiOperation(value = "修改菜单", httpMethod = "DELETE", produces = "application/json", response = Result.class)
  public Result delete(long menuId) {
    if (menuId <= 31) {
      return Result.error("系统菜单，不能删除");
    }

    //判断是否有子菜单或按钮
    List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
    if (menuList.size() > 0) {
      return Result.error("请先删除子菜单或按钮");
    }

    sysMenuService.delete(menuId);

    return Result.ok();
  }

  /**
   * 验证参数是否正确
   */
  private void verifyForm(SysMenuEntity menu) {
    if (StringUtils.isBlank(menu.getName())) {
      throw new ShiroException("菜单名称不能为空");
    }

    if (menu.getParentId() == null) {
      throw new ShiroException("上级菜单不能为空");
    }

    //菜单
    if (menu.getType() == Constant.MenuType.MENU.getValue()) {
      if (StringUtils.isBlank(menu.getUrl())) {
        throw new ShiroException("菜单URL不能为空");
      }
    }

    //上级菜单类型
    int parentType = Constant.MenuType.CATALOG.getValue();
    if (menu.getParentId() != 0) {
      SysMenuEntity parentMenu = sysMenuService.selectById(menu.getParentId());
      parentType = parentMenu.getType();
    }

    //目录、菜单
    if (menu.getType() == Constant.MenuType.CATALOG.getValue() ||
        menu.getType() == Constant.MenuType.MENU.getValue()) {
      if (parentType != Constant.MenuType.CATALOG.getValue()) {
        throw new ShiroException("上级菜单只能为目录类型");
      }
      return;
    }

    //按钮
    if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
      if (parentType != Constant.MenuType.MENU.getValue()) {
        throw new ShiroException("上级菜单只能为菜单类型");
      }
      return;
    }
  }
}

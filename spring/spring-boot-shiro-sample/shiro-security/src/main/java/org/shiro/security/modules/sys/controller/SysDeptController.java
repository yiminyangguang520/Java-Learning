package org.shiro.security.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.shiro.common.utils.Result;
import org.shiro.security.common.utils.Constant;
import org.shiro.security.modules.sys.entity.SysDeptEntity;
import org.shiro.security.modules.sys.service.SysDeptService;
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
 * @version 创建时间：2018年4月25日 上午11:14:33
 * 类说明：部门管理
 */
@RestController
@RequestMapping("/sys/dept")
@Api(value = "部门管理")
public class SysDeptController extends AbstractController {

  @Autowired
  private SysDeptService sysDeptService;

  /**
   * 列表
   */
  @GetMapping("/list")
  @RequiresPermissions("sys:dept:list")
  @ApiOperation(value = "列表", httpMethod = "GET", produces = "application/json", response = Result.class)
  public List<SysDeptEntity> list() {
    List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());
    return deptList;
  }

  /**
   * 选择部门(添加、修改菜单)
   */
  @GetMapping("/select")
  @RequiresPermissions("sys:dept:select")
  @ApiOperation(value = "选择部门(添加、修改菜单)", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result select() {
    List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());
    //添加一级部门
    if (getUserId() == Constant.SUPER_ADMIN) {
      SysDeptEntity root = new SysDeptEntity();
      root.setDeptId(0L);
      root.setName("一级部门");
      root.setParentId(-1L);
      root.setOpen(true);
      deptList.add(root);
    }

    return Result.ok().put("deptList", deptList);
  }

  /**
   * 上级部门Id(管理员则为0)
   */
  @GetMapping("/info")
  @RequiresPermissions("sys:dept:list")
  @ApiOperation(value = "上级部门Id(管理员则为0)", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result info() {
    long deptId = 0;
    try {
      if (getUserId() != Constant.SUPER_ADMIN) {
        List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());
        Long parentId = null;
        for (SysDeptEntity sysDeptEntity : deptList) {
          if (parentId == null) {
            parentId = sysDeptEntity.getParentId();
            continue;
          }

          if (parentId > sysDeptEntity.getParentId().longValue()) {
            parentId = sysDeptEntity.getParentId();
          }
        }
        deptId = parentId;
      }

      return Result.ok().put("deptId", deptId);
    } catch (Exception e) {
      return Result.error(Constant.NOLONIN);
    }

  }

  /**
   * 信息
   */
  @GetMapping("/info/{deptId}")
  @RequiresPermissions("sys:dept:info")
  @ApiOperation(value = "信息", httpMethod = "GET", produces = "application/json", response = Result.class)
  public Result info(@PathVariable("deptId") Long deptId) {
    SysDeptEntity dept = sysDeptService.selectById(deptId);

    return Result.ok().put("dept", dept);
  }

  /**
   * 保存
   */
  @PostMapping("/save")
  @RequiresPermissions("sys:dept:save")
  @ApiOperation(value = "保存", httpMethod = "POST", produces = "application/json", response = Result.class)
  public Result save(@RequestBody SysDeptEntity dept) {
    sysDeptService.insert(dept);

    return Result.ok();
  }

  /**
   * 修改
   */
  @PutMapping("/update")
  @RequiresPermissions("sys:dept:update")
  @ApiOperation(value = "保存", httpMethod = "PUT", produces = "application/json", response = Result.class)
  public Result update(@RequestBody SysDeptEntity dept) {
    sysDeptService.updateById(dept);

    return Result.ok();
  }

  /**
   * 删除
   */
  @DeleteMapping("/delete")
  @RequiresPermissions("sys:dept:delete")
  @ApiOperation(value = "保存", httpMethod = "DELETE", produces = "application/json", response = Result.class)
  public Result delete(long deptId) {
    //判断是否有子部门
    List<Long> deptList = sysDeptService.queryDetpIdList(deptId);
    if (deptList.size() > 0) {
      return Result.error(Constant.DELETECHILE);
    }

    sysDeptService.deleteById(deptId);

    return Result.ok();
  }

}

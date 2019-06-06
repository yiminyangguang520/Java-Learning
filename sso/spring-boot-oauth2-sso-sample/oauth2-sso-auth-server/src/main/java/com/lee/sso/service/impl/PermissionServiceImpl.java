package com.lee.sso.service.impl;

import com.lee.sso.entity.SysPermission;
import com.lee.sso.entity.SysRolePermission;
import com.lee.sso.entity.SysUserRole;
import com.lee.sso.repository.SysPermissionRepository;
import com.lee.sso.repository.SysRolePermissionRepository;
import com.lee.sso.repository.SysUserRoleRepository;
import com.lee.sso.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChengJianSheng
 * @date 2019-02-12
 */
@Service
public class PermissionServiceImpl implements PermissionService {

  /**
   * 偷懒少写两个Service
   */
  @Autowired
  private SysUserRoleRepository sysUserRoleRepository;

  @Autowired
  private SysRolePermissionRepository sysRolePermissionRepository;

  @Autowired
  private SysPermissionRepository sysPermissionRepository;

  @Override
  public List<SysPermission> findByUserId(Integer userId) {
    List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findByUserId(userId);
    if (CollectionUtils.isEmpty(sysUserRoleList)) {
      return null;
    }

    List<Integer> roleIdList = sysUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
    List<SysRolePermission> rolePermissionList = sysRolePermissionRepository.findByRoleIds(roleIdList);
    if (CollectionUtils.isEmpty(rolePermissionList)) {
      return null;
    }

    List<Integer> permissionIdList = rolePermissionList.stream().map(SysRolePermission::getPermissionId).distinct().collect(Collectors.toList());
    List<SysPermission> sysPermissionList = sysPermissionRepository.findByIds(permissionIdList);
    if (CollectionUtils.isEmpty(sysPermissionList)) {
      return null;
    }

    return sysPermissionList;
  }
}

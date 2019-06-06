package com.lee.sso.service;

import com.lee.sso.entity.SysPermission;

import java.util.List;

/**
 * @author ChengJianSheng
 * @date 2019-02-12
 */
public interface PermissionService {

  List<SysPermission> findByUserId(Integer userId);

}

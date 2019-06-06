package com.lee.sso.service;

import com.lee.sso.entity.SysUser;

/**
 * @author ChengJianSheng
 * @date 2019-02-12
 */
public interface UserService {

  SysUser getByUsername(String username);
}

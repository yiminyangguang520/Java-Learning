package com.lee.sso.service.impl;

import com.lee.sso.entity.SysUser;
import com.lee.sso.repository.SysUserRepository;
import com.lee.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChengJianSheng
 * @date 2019-02-12
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private SysUserRepository sysUserRepository;

  @Override
  public SysUser getByUsername(String username) {
    return sysUserRepository.findByUsername(username);
  }
}

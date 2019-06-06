package com.lee.sso.repository;

import com.lee.sso.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ChengJianSheng
 * @date 2019-02-12
 */
public interface SysUserRepository extends JpaSpecificationExecutor<SysUser>, JpaRepository<SysUser, Integer> {

  SysUser findByUsername(String username);
}

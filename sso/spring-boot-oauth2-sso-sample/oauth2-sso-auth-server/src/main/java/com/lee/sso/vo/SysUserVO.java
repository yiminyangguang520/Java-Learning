package com.lee.sso.vo;

import com.lee.sso.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @author ChengJianSheng
 * @date 2019-02-12
 */
@Data
public class SysUserVO extends SysUser {

  /**
   * 权限列表
   */
  private List<String> authorityList;

}

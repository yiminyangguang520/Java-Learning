package org.shiro.security.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import org.shiro.security.modules.sys.entity.SysUserEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:52:59
 * 类说明： 系统用户
 */
public interface SysUserDao extends BaseMapper<SysUserEntity> {

  /**
   * 查询用户的所有权限
   *
   * @param userId 用户ID
   */
  List<String> queryAllPerms(Long userId);

  /**
   * 查询用户的所有菜单ID
   */
  List<Long> queryAllMenuId(Long userId);

}

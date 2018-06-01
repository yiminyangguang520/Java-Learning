package org.shiro.security.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import org.shiro.security.modules.sys.entity.SysUserRoleEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:52:38
 * 类说明：用户与角色对应关系
 */
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

  /**
   * 根据用户ID，获取角色ID列表
   */
  List<Long> queryRoleIdList(Long userId);

  /**
   * 根据角色ID数组，批量删除
   */
  int deleteBatch(Long[] roleIds);
}

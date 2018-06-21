package org.shiro.security.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import org.shiro.security.modules.sys.entity.SysRoleMenuEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:53:28
 * 类说明：角色与菜单对应关系
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuEntity> {

  /**
   * 根据角色ID，获取菜单ID列表
   */

  List<Long> queryMenuIdList(Long roleId);

  /**
   * 根据角色ID数组，批量删除
   */
  int deleteBatch(Long[] roleIds);
}

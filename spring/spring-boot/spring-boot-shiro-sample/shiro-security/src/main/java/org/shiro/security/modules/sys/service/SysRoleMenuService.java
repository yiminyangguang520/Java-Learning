package org.shiro.security.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import org.shiro.security.modules.sys.entity.SysRoleMenuEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:58:19
 * 类说明：角色与菜单对应关系
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

  void saveOrUpdate(Long roleId, List<Long> menuIdList);

  /**
   * 根据角色ID，获取菜单ID列表
   */
  List<Long> queryMenuIdList(Long roleId);

  /**
   * 根据角色ID数组，批量删除
   */
  int deleteBatch(Long[] roleIds);

}

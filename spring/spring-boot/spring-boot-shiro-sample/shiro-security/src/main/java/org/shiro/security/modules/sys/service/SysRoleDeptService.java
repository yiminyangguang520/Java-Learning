package org.shiro.security.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import org.shiro.security.modules.sys.entity.SysRoleDeptEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:58:42
 * 类说明：角色与部门对应关系
 */
public interface SysRoleDeptService extends IService<SysRoleDeptEntity> {

  void saveOrUpdate(Long roleId, List<Long> deptIdList);

  /**
   * 根据角色ID，获取部门ID列表
   */
  List<Long> queryDeptIdList(Long[] roleIds);

  /**
   * 根据角色ID数组，批量删除
   */
  int deleteBatch(Long[] roleIds);
}

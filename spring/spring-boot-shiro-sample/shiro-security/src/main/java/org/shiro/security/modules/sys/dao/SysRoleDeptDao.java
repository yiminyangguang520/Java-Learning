package org.shiro.security.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import org.shiro.security.modules.sys.entity.SysRoleDeptEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:53:52
 * 类说明：角色与部门对应关系
 */
public interface SysRoleDeptDao extends BaseMapper<SysRoleDeptEntity> {

  /**
   * 根据角色ID，获取部门ID列表
   */
  List<Long> queryDeptIdList(Long[] roleIds);

  /**
   * 根据角色ID数组，批量删除
   */
  int deleteBatch(Long[] roleIds);
}

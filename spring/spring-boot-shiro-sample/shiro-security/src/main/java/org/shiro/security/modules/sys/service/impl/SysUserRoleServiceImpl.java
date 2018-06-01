package org.shiro.security.modules.sys.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.shiro.common.utils.MapUtils;
import org.shiro.security.modules.sys.dao.SysUserRoleDao;
import org.shiro.security.modules.sys.entity.SysUserRoleEntity;
import org.shiro.security.modules.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午11:02:30
 * 类说明：用户与角色对应关系
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

  @Override
  public void saveOrUpdate(Long userId, List<Long> roleIdList) {
    //先删除用户与角色关系
    this.deleteByMap(new MapUtils().put("user_id", userId));

    if (roleIdList == null || roleIdList.size() == 0) {
      return;
    }

    //保存用户与角色关系
    List<SysUserRoleEntity> list = new ArrayList<>(roleIdList.size());
    for (Long roleId : roleIdList) {
      SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
      sysUserRoleEntity.setUserId(userId);
      sysUserRoleEntity.setRoleId(roleId);

      list.add(sysUserRoleEntity);
    }
    this.insertBatch(list);
  }

  @Override
  public List<Long> queryRoleIdList(Long userId) {
    return baseMapper.queryRoleIdList(userId);
  }

  @Override
  public int deleteBatch(Long[] roleIds) {
    return baseMapper.deleteBatch(roleIds);
  }
}

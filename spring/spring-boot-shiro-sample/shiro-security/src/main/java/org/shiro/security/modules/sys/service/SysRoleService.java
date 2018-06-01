package org.shiro.security.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import java.util.Map;
import org.shiro.common.utils.PageUtils;
import org.shiro.security.modules.sys.entity.SysRoleEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:57:52
 * 类说明：角色
 */
public interface SysRoleService extends IService<SysRoleEntity> {

  PageUtils queryPage(Map<String, Object> params);

  void save(SysRoleEntity role);

  void update(SysRoleEntity role);

  void deleteBatch(Long[] roleIds);

}

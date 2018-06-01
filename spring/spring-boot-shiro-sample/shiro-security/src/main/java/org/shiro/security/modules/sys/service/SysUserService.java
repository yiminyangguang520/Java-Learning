package org.shiro.security.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
import org.shiro.common.utils.PageUtils;
import org.shiro.security.modules.sys.entity.SysUserEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:57:03
 * 类说明：系统用户
 */
public interface SysUserService extends IService<SysUserEntity> {

  PageUtils queryPage(Map<String, Object> params);

  /**
   * 查询用户的所有菜单ID
   */
  List<Long> queryAllMenuId(Long userId);

  /**
   * 保存用户
   */
  void save(SysUserEntity user);

  /**
   * 修改用户
   */
  void update(SysUserEntity user);

  /**
   * 修改密码
   *
   * @param userId 用户ID
   * @param password 原密码
   * @param newPassword 新密码
   */
  boolean updatePassword(Long userId, String password, String newPassword);
}

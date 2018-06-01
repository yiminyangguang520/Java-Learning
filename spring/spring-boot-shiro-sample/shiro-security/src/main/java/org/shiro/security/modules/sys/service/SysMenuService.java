package org.shiro.security.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import org.shiro.security.modules.sys.entity.SysMenuEntity;

/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:59:07
 * 类说明：菜单管理
 */
public interface SysMenuService extends IService<SysMenuEntity> {

  /**
   * 根据父菜单，查询子菜单
   *
   * @param parentId 父菜单ID
   * @param menuIdList 用户菜单ID
   */
  List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

  /**
   * 根据父菜单，查询子菜单
   *
   * @param parentId 父菜单ID
   */

  List<SysMenuEntity> queryListParentId(Long parentId);

  /**
   * 获取不包含按钮的菜单列表
   */
  List<SysMenuEntity> queryNotButtonList();

  /**
   * 获取用户菜单列表
   */
  List<SysMenuEntity> getUserMenuList(Long userId);

  /**
   * 删除
   */
  void delete(Long menuId);
}

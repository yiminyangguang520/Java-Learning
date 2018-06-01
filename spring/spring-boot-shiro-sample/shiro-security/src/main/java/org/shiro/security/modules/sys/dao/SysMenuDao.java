package org.shiro.security.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import org.shiro.security.modules.sys.entity.SysMenuEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:54:38
 * 类说明：菜单管理
 */
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

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

}

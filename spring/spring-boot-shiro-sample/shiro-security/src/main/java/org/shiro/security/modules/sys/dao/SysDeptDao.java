package org.shiro.security.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import org.shiro.security.modules.sys.entity.SysDeptEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:55:41
 * 类说明：部门管理
 */
public interface SysDeptDao extends BaseMapper<SysDeptEntity> {

  /**
   * 查询子部门ID列表
   *
   * @param parentId 上级部门ID
   */

  List<Long> queryDetpIdList(Long parentId);

}

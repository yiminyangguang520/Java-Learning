package org.shiro.security.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
import org.shiro.security.modules.sys.entity.SysDeptEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午11:00:27
 * 类说明：部门管理
 */
public interface SysDeptService extends IService<SysDeptEntity> {

  List<SysDeptEntity> queryList(Map<String, Object> map);

  /**
   * 查询子部门ID列表
   *
   * @param parentId 上级部门ID
   */
  List<Long> queryDetpIdList(Long parentId);

  /**
   * 获取子部门ID，用于数据过滤
   */
  List<Long> getSubDeptIdList(Long deptId);

}

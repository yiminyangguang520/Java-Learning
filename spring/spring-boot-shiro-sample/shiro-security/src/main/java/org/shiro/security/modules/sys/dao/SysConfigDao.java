package org.shiro.security.modules.sys.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.shiro.security.modules.sys.entity.SysConfigEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:56:08
 * 类说明：系统配置信息
 */
public interface SysConfigDao extends BaseMapper<SysConfigEntity> {

  /**
   * 根据key，查询value
   */
  SysConfigEntity queryByKey(String paramKey);

  /**
   * 根据key，更新value
   */
  int updateValueByKey(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue);

}

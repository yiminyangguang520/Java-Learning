package org.shiro.security.modules.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.Map;
import org.shiro.security.modules.job.entity.ScheduleJobEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:04:56
 * 类说明：定时任务
 */
public interface ScheduleJobDao extends BaseMapper<ScheduleJobEntity> {

  /**
   * 批量更新状态
   */
  int updateBatch(Map<String, Object> map);
}

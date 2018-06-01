package org.shiro.security.modules.job.service;

import com.baomidou.mybatisplus.service.IService;
import java.util.Map;
import org.shiro.common.utils.PageUtils;
import org.shiro.security.modules.job.entity.ScheduleJobEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:23:02
 * 类说明：定时任务
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {

  PageUtils queryPage(Map<String, Object> params);

  /**
   * 保存定时任务
   */
  void save(ScheduleJobEntity scheduleJob);

  /**
   * 更新定时任务
   */
  void update(ScheduleJobEntity scheduleJob);

  /**
   * 批量删除定时任务
   */
  void deleteBatch(Long[] jobIds);

  /**
   * 批量更新定时任务状态
   */
  int updateBatch(Long[] jobIds, int status);

  /**
   * 立即执行
   */
  void run(Long[] jobIds);

  /**
   * 暂停运行
   */
  void pause(Long[] jobIds);

  /**
   * 恢复运行
   */
  void resume(Long[] jobIds);
}

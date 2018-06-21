package org.shiro.security.modules.job.service;

import com.baomidou.mybatisplus.service.IService;
import java.util.Map;
import org.shiro.common.utils.PageUtils;
import org.shiro.security.modules.job.entity.ScheduleJobLogEntity;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:21:57
 * 类说明：定时任务日志
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

  PageUtils queryPage(Map<String, Object> params);

}

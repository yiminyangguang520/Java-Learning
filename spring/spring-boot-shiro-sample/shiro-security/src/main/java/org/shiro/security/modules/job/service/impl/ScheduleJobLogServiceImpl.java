package org.shiro.security.modules.job.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.shiro.common.utils.PageUtils;
import org.shiro.security.common.utils.Query;
import org.shiro.security.modules.job.dao.ScheduleJobLogDao;
import org.shiro.security.modules.job.entity.ScheduleJobLogEntity;
import org.shiro.security.modules.job.service.ScheduleJobLogService;
import org.springframework.stereotype.Service;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:24:23
 * 类说明：定时任务日志实现
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    String jobId = (String) params.get("jobId");

    Page<ScheduleJobLogEntity> page = this.selectPage(
        new Query<ScheduleJobLogEntity>(params).getPage(),
        new EntityWrapper<ScheduleJobLogEntity>().like(StringUtils.isNotBlank(jobId), "job_id", jobId)
    );

    return new PageUtils(page);
  }

}

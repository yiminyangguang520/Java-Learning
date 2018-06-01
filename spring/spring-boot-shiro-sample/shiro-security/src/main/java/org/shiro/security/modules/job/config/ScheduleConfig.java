package org.shiro.security.modules.job.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:03:46
 * 类说明：定时任务配置
 */
@Configuration
public class ScheduleConfig {

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
    SchedulerFactoryBean factory = new SchedulerFactoryBean();
    factory.setDataSource(dataSource);

    //quartz参数
    Properties prop = new Properties();
    prop.put("org.quartz.scheduler.instanceName", "SMScheduler");
    prop.put("org.quartz.scheduler.instanceId", "AUTO");
    //线程池配置
    prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
    prop.put("org.quartz.threadPool.threadCount", "20");
    prop.put("org.quartz.threadPool.threadPriority", "5");
    //JobStore配置
    prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
    //集群配置
    prop.put("org.quartz.jobStore.isClustered", "true");
    prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
    prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");

    prop.put("org.quartz.jobStore.misfireThreshold", "12000");
    prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
    prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS UPDLOCK WHERE LOCK_NAME = ?");

    //PostgreSQL数据库，需要打开此注释
    //prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");

    factory.setQuartzProperties(prop);

    factory.setSchedulerName("SMScheduler");
    //延时启动
    factory.setStartupDelay(30);
    factory.setApplicationContextSchedulerContextKey("applicationContextKey");
    //可选，QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
    factory.setOverwriteExistingJobs(true);
    //设置自动启动，默认为true
    factory.setAutoStartup(true);

    return factory;
  }
}

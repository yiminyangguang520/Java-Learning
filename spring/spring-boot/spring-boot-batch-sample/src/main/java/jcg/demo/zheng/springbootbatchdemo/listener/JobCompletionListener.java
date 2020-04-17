package jcg.demo.zheng.springbootbatchdemo.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class JobCompletionListener extends JobExecutionListenerSupport {

  @Override
  public void afterJob(JobExecution jobExecution) {

    StringBuilder msg = new StringBuilder();
    msg.append("##### Finishing Job Name=").append(jobExecution.getJobInstance().getJobName())
        .append(" JOB_EXE_ID=").append(jobExecution.getId()).append(" JOB_ID=").append(jobExecution.getJobId())
        .append(", Status=").append(jobExecution.getStatus()).append(", StartTime=")
        .append(jobExecution.getStartTime()).append(", EndTime=").append(jobExecution.getEndTime());
    System.out.println(msg);
  }

}

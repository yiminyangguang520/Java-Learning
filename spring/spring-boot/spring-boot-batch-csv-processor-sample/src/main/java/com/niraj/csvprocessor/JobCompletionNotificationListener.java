package com.niraj.csvprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      Integer teenager = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TEENAGER ", Integer.class);
      Integer youngadult = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM YOUNGADULT ", Integer.class);
      Integer seniorcitizens = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM SENIORCITIZENS", Integer.class);

      log.info("Total TeenAge rows {}", teenager);
      log.info("Total yongAdult rows {}", youngadult);
      log.info("Total seniorcitizens rows {}", seniorcitizens);
			/*jdbcTemplate.query("SELECT first_name, last_name FROM people",
				(rs, row) -> Person.builder().firstName(rs.getString(1))
											 .lastName(rs.getString(2)).build()	
					
			).forEach(person -> log.info("Found <" + person + "> in the database."));*/
    }
  }
}

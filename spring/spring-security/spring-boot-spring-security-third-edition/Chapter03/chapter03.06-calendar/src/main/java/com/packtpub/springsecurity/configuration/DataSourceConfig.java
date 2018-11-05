package com.packtpub.springsecurity.configuration;

import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author litz-a
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private EmbeddedDatabase database = null;

  /**
   * DataSource PostConstruct call-back
   */
  @PostConstruct
  public void dataSourceInitialization() {
    // h2 admin via hsql Database Manager
//        DatabaseManagerSwing.main(new String[] { "--url", "jdbc:h2:mem:dataSource", "--user", "sa", "--password", "" });
  }

  /**
   * DataSource PreDestroy call-back
   */
  @PreDestroy()
  public void dataSourceDestroy() throws SQLException {
    if (database != null) {
      database.shutdown();
    }
  }

}

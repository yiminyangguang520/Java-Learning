package com.packtpub.springsecurity.configuration;

import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Mick Knutson
 * @since chapter 04.05
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * Custom H2 implementation for our {@link EmbeddedDatabase}
   */
  @Bean
  public DataSource dataSource() {

    // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
    return new EmbeddedDatabaseBuilder()
        //Starting embedded database: url='jdbc:h2:mem:dataSource;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false', username='sa'
        .setName("dataSource")
        // Lets not get upset as we are only debugging ;-)
        .ignoreFailedDrops(true)
        .continueOnError(true)
        // DB Details:
        .setType(EmbeddedDatabaseType.H2)
        .addScript("/database/h2/calendar-schema.sql")
        .addScript("/database/h2/calendar-data.sql")
        // Authority tables
        .addScript("/database/h2/calendar-authorities.sql")
//                .addScript("/database/h2/calendar-saltedsha256.sql")
        .addScript("/database/h2/calendar-bcrypt.sql")
        .build();
  }

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
  }

} // The End...

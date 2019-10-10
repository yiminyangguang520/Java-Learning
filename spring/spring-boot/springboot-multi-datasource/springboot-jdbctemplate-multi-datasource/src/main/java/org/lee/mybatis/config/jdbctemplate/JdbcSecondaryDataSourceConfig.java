package org.lee.mybatis.config.jdbctemplate;

import javax.sql.DataSource;
import org.lee.mybatis.config.SecondaryDataSourceConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author bruce
 */
@Configuration
@Import({SecondaryDataSourceConfig.class})
public class JdbcSecondaryDataSourceConfig {

  @Bean(name = "secondaryJdbcTemplate")
  @Primary
  public JdbcTemplate secondaryJdbcTemplate(
    @Qualifier("secondaryDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}

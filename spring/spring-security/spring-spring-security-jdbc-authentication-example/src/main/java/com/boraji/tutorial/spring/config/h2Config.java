package com.boraji.tutorial.spring.config;

import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * @author litz-a
 */
@Configuration
@PropertySource("classpath:h2.properties")
public class h2Config {

  @Value("classpath:schema.sql")
  private Resource H2_SCHEMA_SCRIPT;

  @Value("classpath:data.sql")
  private Resource H2_DATA_SCRIPT;

  @Value("classpath:drop-data.sql")
  private Resource H2_CLEANER_SCRIPT;

  @Autowired
  private Environment env;

  /**
   * h2SampleDataSource和h2DataSource任选其一即可
   * @return
   */
  @Bean("h2SampleDataSource")
  public DataSource h2SampleDataSource() {
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    EmbeddedDatabase dataSource = builder
        .setName("dataSource")
        .addScript("classpath:schema.sql")
        .addScript("classpath:data.sql")
        .setScriptEncoding("UTF-8")
        .ignoreFailedDrops(true)
        .setType(EmbeddedDatabaseType.H2)
        .build();
    return dataSource;
  }

//  @Bean("h2DataSource")
//  public DataSource h2DataSource() {
//    return createH2DataSource();
//  }
//
//  @Bean
//  @Autowired
//  public DataSourceInitializer dataSourceInitializer(final DataSource h2DataSource) {
//    final DataSourceInitializer initializer = new DataSourceInitializer();
//    initializer.setDataSource(h2DataSource);
//    initializer.setDatabasePopulator(databasePopulator());
//    initializer.setDatabaseCleaner(databaseCleaner());
//    return initializer;
//  }
//
//  private DatabasePopulator databasePopulator() {
//    final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//    populator.addScript(H2_SCHEMA_SCRIPT);
//    populator.addScript(H2_DATA_SCRIPT);
//    return populator;
//  }
//
//  private DatabasePopulator databaseCleaner() {
//    final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//    populator.addScript(H2_CLEANER_SCRIPT);
//    return populator;
//  }
//
//  private DataSource createH2DataSource() {
//    JdbcDataSource ds = new JdbcDataSource();
//    ds.setURL(env.getProperty("h2.jdbcUrl"));
//    ds.setUser(env.getProperty("h2.username"));
//    ds.setPassword(env.getProperty("h2.password"));
//    return ds;
//  }
}

package com.walking.techie.mysql.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Data
public abstract class AbstractMySQLConfig {

  // MySQL DB Properties
  private String url, username, password;

  public abstract DataSource dataSource();

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setGenerateDdl(true);
    adapter.setShowSql(true);
    return adapter;
  }
}

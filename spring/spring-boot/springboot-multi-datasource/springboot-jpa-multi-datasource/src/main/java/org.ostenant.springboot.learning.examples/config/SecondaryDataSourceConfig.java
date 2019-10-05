package org.ostenant.springboot.learning.examples.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.secondary")
@Data
public class SecondaryDataSourceConfig {

    @NotBlank
    private String driverClassName;
    @NotBlank
    private String url;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private Integer maxActive;
    private Boolean poolPreparedStatements;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    private Integer minIdle;
    private Long timeBetweenEvictionRunsMillis;
    private Long minEvictableIdleTimeMillis;
    private String validationQuery;

    private Boolean testWhileIdle;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;

    @Bean(name = "secondaryDataSource", initMethod = "init", destroyMethod = "close")
    public DataSource secondaryDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(getDriverClassName());
        druidDataSource.setUrl(getUrl());
        druidDataSource.setUsername(getUsername());
        druidDataSource.setPassword(getPassword());

        druidDataSource.setMaxActive(getMaxActive());
        druidDataSource.setPoolPreparedStatements(getPoolPreparedStatements());

        druidDataSource.setTimeBetweenEvictionRunsMillis(getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(getMinEvictableIdleTimeMillis());
        druidDataSource.setMinIdle(getMinIdle());
        druidDataSource.setValidationQuery(getValidationQuery());
        druidDataSource.setTestWhileIdle(getTestWhileIdle());
        druidDataSource.setTestOnBorrow(getTestOnBorrow());
        druidDataSource.setTestOnReturn(getTestOnReturn());

        return druidDataSource;
    }

}

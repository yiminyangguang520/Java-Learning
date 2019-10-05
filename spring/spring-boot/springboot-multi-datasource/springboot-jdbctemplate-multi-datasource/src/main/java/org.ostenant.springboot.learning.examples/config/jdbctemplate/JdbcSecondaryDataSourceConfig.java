package org.ostenant.springboot.learning.examples.config.jdbctemplate;

import org.ostenant.springboot.learning.examples.config.SecondaryDataSourceConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

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

package org.ostenant.springboot.learning.examples.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.ostenant.springboot.learning.examples.config.PrimaryDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import({PrimaryDataSourceConfig.class})
@MapperScan(
        basePackages = {MybatisPrimaryDataSourceConfig.PACKAGE},
        sqlSessionFactoryRef = "primarySqlSessionFactory",
        sqlSessionTemplateRef = "primarySqlSessionTemplate"
)
public class MybatisPrimaryDataSourceConfig {

    // Primary - Mapper接口的的基础包
    static final String PACKAGE = "org.ostenant.springboot.learning.examples.mapper.primary";
    // Primary - Mapper.xml配置文件的路径
    private static final String MAPPER_LOCATION = "classpath:mapper/primary/*.xml";
    // Primary - Model所在包
    private static final String TYPE_ALIAS_PACKAGE = "org.ostenant.springboot.learning.examples.model.primary";

    private final DataSource primaryDataSource;

    @Autowired
    public MybatisPrimaryDataSourceConfig(@Qualifier("primaryDataSource") DataSource primaryDataSource) {
        this.primaryDataSource = primaryDataSource;
    }

    @Bean(name = "primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager primaryTransactionManager() {
        return new DataSourceTransactionManager(primaryDataSource);
    }

    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sessionFactoryBean.setDataSource(primaryDataSource);
        // 设置Model别名所在包路径
        sessionFactoryBean.setTypeAliasesPackage(MybatisPrimaryDataSourceConfig.TYPE_ALIAS_PACKAGE);
        // 设置Mapper.xml所在包路径
        sessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources(MybatisPrimaryDataSourceConfig.MAPPER_LOCATION)
        );

        return sessionFactoryBean.getObject();
    }

    @Bean(name = "primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(primarySqlSessionFactory());
    }

}

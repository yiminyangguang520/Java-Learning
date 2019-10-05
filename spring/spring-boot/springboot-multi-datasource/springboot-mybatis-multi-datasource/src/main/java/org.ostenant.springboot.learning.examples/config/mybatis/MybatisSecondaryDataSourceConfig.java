package org.ostenant.springboot.learning.examples.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.ostenant.springboot.learning.examples.config.SecondaryDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import({SecondaryDataSourceConfig.class})
@MapperScan(
        basePackages = {MybatisSecondaryDataSourceConfig.PACKAGE},
        sqlSessionFactoryRef = "secondarySqlSessionFactory",
        sqlSessionTemplateRef = "secondarySqlSessionTemplate"
)
public class MybatisSecondaryDataSourceConfig {

    // Secondary - Mapper接口的的基础包
    static final String PACKAGE = "org.ostenant.springboot.learning.examples.mapper.secondary";
    // Secondary - Mapper.xml配置文件的路径
    private static final String MAPPER_LOCATION = "classpath:mapper/secondary/*.xml";
    // Secondary - Model所在包
    private static final String TYPE_ALIAS_PACKAGE = "org.ostenant.springboot.learning.examples.model.secondary";

    private final DataSource secondaryDataSource;

    @Autowired
    public MybatisSecondaryDataSourceConfig(@Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
        this.secondaryDataSource = secondaryDataSource;
    }

    @Bean(name = "secondaryTransactionManager")
    public DataSourceTransactionManager secondaryTransactionManager() {
        return new DataSourceTransactionManager(secondaryDataSource);
    }

    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sessionFactoryBean.setDataSource(secondaryDataSource);
        // 设置Model别名所在包路径
        sessionFactoryBean.setTypeAliasesPackage(MybatisSecondaryDataSourceConfig.TYPE_ALIAS_PACKAGE);
        // 设置Mapper.xml所在包路径
        sessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources(MybatisSecondaryDataSourceConfig.MAPPER_LOCATION)
        );

        return sessionFactoryBean.getObject();
    }

    @Bean(name = "secondarySqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(secondarySqlSessionFactory());
    }

}
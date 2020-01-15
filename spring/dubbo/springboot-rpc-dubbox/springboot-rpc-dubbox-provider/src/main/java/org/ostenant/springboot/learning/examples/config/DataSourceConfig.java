package org.ostenant.springboot.learning.examples.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {
    /**
     * 基础数据库访问配置
     */
    private String type;
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    /**
     * 下面为连接池的补充设置
     */
    private int initialSize;
    private int maxActive;
    private int minIdle;

    /**
     * 配置获取连接等待超时的时间
     */
    private int maxWait;

    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;

    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    private int maxPoolPreparedStatementPerConnectionSize;

    /**
     * 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
     */
    private String filters;

    private String poolPreparedStatements;
    private String connectionProperties;
    private String validationQuery;

    public String getType() {
        return type;
    }

    public DataSourceConfig setType(String type) {
        this.type = type;
        return this;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public DataSourceConfig setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public DataSourceConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DataSourceConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DataSourceConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public DataSourceConfig setInitialSize(int initialSize) {
        this.initialSize = initialSize;
        return this;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public DataSourceConfig setMaxActive(int maxActive) {
        this.maxActive = maxActive;
        return this;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public DataSourceConfig setMaxWait(int maxWait) {
        this.maxWait = maxWait;
        return this;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public DataSourceConfig setMinIdle(int minIdle) {
        this.minIdle = minIdle;
        return this;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public DataSourceConfig setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        return this;
    }

    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public DataSourceConfig setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
        return this;
    }

    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public DataSourceConfig setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
        return this;
    }

    public String getPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public DataSourceConfig setPoolPreparedStatements(String poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
        return this;
    }

    public String getConnectionProperties() {
        return connectionProperties;
    }

    public DataSourceConfig setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
        return this;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public DataSourceConfig setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
        return this;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public DataSourceConfig setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
        return this;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public DataSourceConfig setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
        return this;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public DataSourceConfig setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
        return this;
    }

    public String getFilters() {
        return filters;
    }

    public DataSourceConfig setFilters(String filters) {
        this.filters = filters;
        return this;
    }
}



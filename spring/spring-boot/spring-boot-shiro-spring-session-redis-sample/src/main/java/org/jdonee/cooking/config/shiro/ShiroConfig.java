package org.jdonee.cooking.config.shiro;

import com.google.common.collect.Maps;
import java.time.Duration;
import java.util.Map;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.jdonee.cooking.config.redis.CustomRedisProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author litz-a
 */
@Configuration
@Slf4j
public class ShiroConfig {

  /**
   * 加载属性文件数据
   */
  @Bean
  public CustomRedisProperties shiroProperties() {
    return new CustomRedisProperties();
  }

  /**
   * FilterRegistrationBean
   */
  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
    filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
    filterRegistration.setEnabled(true);
    filterRegistration.addUrlPatterns("/*");
    filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
    return filterRegistration;
  }

  /**
   * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean
   */
  @Bean(name = "shiroFilter")
  public ShiroFilterFactoryBean shiroFilter() {
    ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
    bean.setSecurityManager(securityManager());
    bean.setLoginUrl("/login");
    bean.setUnauthorizedUrl("/unauthor");

    Map<String, Filter> filters = Maps.newHashMap();
    filters.put("perms", urlPermissionsFilter());
    filters.put("anon", new AnonymousFilter());
    bean.setFilters(filters);

    Map<String, String> chains = Maps.newHashMap();
    chains.put("/druid/**", "anon");
    chains.put("/login", "anon");
    chains.put("/unauthor", "anon");
    chains.put("/logout", "logout");
    chains.put("/base/**", "anon");
    chains.put("/css/**", "anon");
    chains.put("/layer/**", "anon");
    chains.put("/**", "authc,perms");
    bean.setFilterChainDefinitionMap(chains);
    return bean;
  }

  /**
   * 权限管理器
   */
  @Bean(name = "securityManager")
  public DefaultWebSecurityManager securityManager() {
    DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
    // 数据库认证的实现
    manager.setRealm(userRealm());
    // session 管理器
    manager.setSessionManager(sessionManager());
    // 缓存管理器
    manager.setCacheManager(redisCacheManager());
    return manager;
  }

  /**
   * DefaultWebSessionManager
   */
  @Bean(name = "sessionManager")
  public ServletContainerSessionManager sessionManager() {
    ServletContainerSessionManager sessionManager = new ServletContainerSessionManager();
    return sessionManager;
  }

  /**
   * @see UserRealm--->AuthorizingRealm
   */
  @Bean
  @DependsOn(value = {"lifecycleBeanPostProcessor", "shrioRedisCacheManager"})
  public UserRealm userRealm() {
    UserRealm userRealm = new UserRealm();
    userRealm.setCacheManager(redisCacheManager());
    userRealm.setCachingEnabled(true);
    //禁用认证缓存
    userRealm.setAuthenticationCachingEnabled(false);
    userRealm.setAuthorizationCachingEnabled(true);
    return userRealm;
  }

  @Bean
  public URLPermissionsFilter urlPermissionsFilter() {
    return new URLPermissionsFilter();
  }

  @Bean(name = "shrioRedisCacheManager")
  @DependsOn(value = "shiroRedisTemplate")
  public ShrioRedisCacheManager redisCacheManager() {
    ShrioRedisCacheManager cacheManager = new ShrioRedisCacheManager(shiroRedisTemplate());
    cacheManager.createCache("shiro_redis:");
    return cacheManager;
  }

  @Bean(name = "shiroRedisTemplate")
  public RedisTemplate<byte[], byte[]> shiroRedisTemplate() {
    RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
    template.setConnectionFactory(lettuceConnectionFactory());
    return template;
  }


  @Bean
  public RedisStandaloneConfiguration getRedisStandaloneConfiguration() {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(shiroProperties().getHost());
    redisStandaloneConfiguration.setPort(shiroProperties().getPort());
    redisStandaloneConfiguration.setPassword(RedisPassword.of(shiroProperties().getPassword()));
    redisStandaloneConfiguration.setDatabase(shiroProperties().getSecondaryDatabase());
    return redisStandaloneConfiguration;
  }

  @Bean
  public LettuceClientConfiguration getLettuceClientConfiguration() {
    return LettuceClientConfiguration.builder().build();
  }

  /**
   * Redis连接客户端(Session及Shiro缓存管理)
   */
  @Primary
  @Bean(name = "lettuceConnectionFactory")
  public LettuceConnectionFactory lettuceConnectionFactory() {
    LettuceConnectionFactory conn = new LettuceConnectionFactory(getRedisStandaloneConfiguration(),
        getLettuceClientConfiguration());
    log.info("1.初始化Redis缓存服务器(登录用户Session及Shiro缓存管理)... ...");
    return conn;
  }

  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

}
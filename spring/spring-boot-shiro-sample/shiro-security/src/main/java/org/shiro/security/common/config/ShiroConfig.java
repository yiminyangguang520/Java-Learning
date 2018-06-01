package org.shiro.security.common.config;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.shiro.security.modules.sys.shiro.RedisShiroSessionDAO;
import org.shiro.security.modules.sys.shiro.UserRealm;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:42:19
 * 类说明：Shiro的配置文件
 */
@Configuration
public class ShiroConfig {

  @Bean("sessionManager")
  public SessionManager sessionManager(RedisShiroSessionDAO redisShiroSessionDAO,
      @Value("${shiro.redis.open}") boolean redisOpen,
      @Value("${shiro.shiro.redis}") boolean shiroRedis, @Value("${shiro.session.time}") Integer sessionTime) {
    DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
    sessionManager.setGlobalSessionTimeout(sessionTime);
    sessionManager.setSessionValidationSchedulerEnabled(true);
    sessionManager.setSessionIdUrlRewritingEnabled(false);
    //如果开启redis缓存且shiro.shiro.redis=true，则shiro session存到redis里
    if (redisOpen && shiroRedis) {
      sessionManager.setSessionDAO(redisShiroSessionDAO);
    }
    return sessionManager;
  }

  @Bean("securityManager")
  public SecurityManager securityManager(UserRealm userRealm, SessionManager sessionManager) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(userRealm);
    securityManager.setSessionManager(sessionManager);
    return securityManager;
  }
    
    
/*    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(UserRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 使用自己的realm
        manager.setRealm(realm);
        
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);

        return manager;
    }*/


  @Bean("shiroFilter")
  public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    shiroFilter.setSecurityManager(securityManager);
    Map<String, String> filterMap = new LinkedHashMap<>();
    filterMap.put("/swagger/**", "anon");
    filterMap.put("/v2/api-docs", "anon");
    filterMap.put("/swagger-ui.html", "anon");
    filterMap.put("/webjars/**", "anon");
    filterMap.put("/swagger-resources/**", "anon");
    filterMap.put("/statics/**", "anon");
    filterMap.put("/sys/login", "anon");
    filterMap.put("/logout", "anon");
    filterMap.put("/favicon.ico", "anon");
    filterMap.put("/**", "anon");
    shiroFilter.setFilterChainDefinitionMap(filterMap);
    return shiroFilter;
  }

  @Bean("lifecycleBeanPostProcessor")
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  @Bean
  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
    proxyCreator.setProxyTargetClass(true);
    return proxyCreator;
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);
    return advisor;
  }

}

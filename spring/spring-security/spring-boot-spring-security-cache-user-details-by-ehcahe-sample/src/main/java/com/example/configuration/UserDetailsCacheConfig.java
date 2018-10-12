package com.example.configuration;

import java.lang.reflect.Constructor;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.authentication.CachingUserDetailsService;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.EhCacheBasedUserCache;
import org.springframework.util.Assert;

/**
 * @author litz-a
 */
@EnableCaching
@Configuration
public class UserDetailsCacheConfig {

  @Autowired
  @Qualifier("userService")
  private UserDetailsService userDetailsService;

  @Bean
  public UserCache userCache() {
    EhCacheBasedUserCache userCache = new EhCacheBasedUserCache();
    CacheManager cacheManager = CacheManager.getInstance();
    Cache cache = cacheManager.getCache("userDetailsCache");
    userCache.setCache(cache);
    return userCache;
  }

  @Bean
  public UserDetailsService cachingUserDetailsService() {
    Constructor<CachingUserDetailsService> ctor = null;
    try {
      ctor = CachingUserDetailsService.class.getDeclaredConstructor(UserDetailsService.class);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    Assert.notNull(ctor, "CachingUserDetailsService constructor is null");
    ctor.setAccessible(true);

    CachingUserDetailsService cachingUserDetailsService = BeanUtils.instantiateClass(ctor, userDetailsService);
    cachingUserDetailsService.setUserCache(userCache());
    return cachingUserDetailsService;
  }
}

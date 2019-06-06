package com.example.webfluxsessiondemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

/**
 * @author wangxing
 * @create 2019/5/15
 */
@Configuration
@EnableRedisWebSession(redisFlushMode = RedisFlushMode.IMMEDIATE)
public class SpringSessionConfig {

}
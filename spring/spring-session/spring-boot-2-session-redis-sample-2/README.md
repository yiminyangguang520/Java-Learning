# spring-boot-redis-session
分布式项目session内容共享到redis中

> Spring 本身提供了redis 存储 session 的实现，但是在使用到微信的项目时，会不太好使就做了简单的redis 实现

主要实现了spring提供的ExpiringSession, SessionRepository, HttpSessionStrategy 接口
- WxHttpSessionStrategy
- WxRedisSession
- WxRedisSessionRepository

配置到spring boot项目中
- HttpSessionConfig
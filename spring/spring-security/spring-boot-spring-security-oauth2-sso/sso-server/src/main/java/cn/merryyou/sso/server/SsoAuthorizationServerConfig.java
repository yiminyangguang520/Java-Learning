package cn.merryyou.sso.server;

import cn.merryyou.sso.config.Jackson2SerializationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Created on 2017/12/26.
 *
 * @author zlf
 * @since 1.0
 */
@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  @Autowired
  private Jackson2SerializationStrategy jackson2SerializationStrategy;

  /**
   * 客户端一些配置
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient("merryyou1")
        .secret(new BCryptPasswordEncoder().encode("merryyousecrect1"))
        .authorizedGrantTypes("authorization_code", "refresh_token")
        .redirectUris("http://xxlssoclient1.com:8083/client1/login")
        .scopes("all", "read", "write")
        .autoApprove(true)

        .and()

        .withClient("merryyou2")
        .secret(new BCryptPasswordEncoder().encode("merryyousecrect2"))
        .authorizedGrantTypes("authorization_code", "refresh_token")
//      .redirectUris("http://sso-tmall:8084/client2")
        .scopes("all", "read", "write")
        .autoApprove(true);
  }

  /**
   * 配置jwttokenStore
   */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(tokenStore());
  }

  /**
   * springSecurity 授权表达式，访问merryyou tokenkey时需要经过认证
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("isAuthenticated()")
        .checkTokenAccess("isAuthenticated()");
  }

  @Bean
  public TokenStore tokenStore() {
    RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
//    redisTokenStore.setSerializationStrategy(jackson2SerializationStrategy);
    return redisTokenStore;
  }
}

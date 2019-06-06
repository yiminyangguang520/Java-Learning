package com.xingfly.xfauth.config;

import com.xingfly.xfauth.service.security.JPAUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private RedisConnectionFactory connectionFactory;

  @Autowired
  private JPAUserDetailsService jpaUserDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Bean
  public RedisTokenStore tokenStore() {
    return new RedisTokenStore(connectionFactory);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        .authenticationManager(authenticationManager)
        .userDetailsService(jpaUserDetailsService)//若无，refresh_token会有UserDetailsService is required错误
        .tokenStore(tokenStore());
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security
        .allowFormAuthenticationForClients()
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()");
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient("android")
        .scopes("xx")
        .secret(passwordEncoder.encode("android"))
        .authorizedGrantTypes("password", "authorization_code", "refresh_token")
        .and()
        .withClient("webapp")
        .scopes("xx")
        .authorizedGrantTypes("implicit")
        .and()
        .withClient("browser")
        .authorizedGrantTypes("refresh_token", "password")
        .scopes("ui");
  }
}

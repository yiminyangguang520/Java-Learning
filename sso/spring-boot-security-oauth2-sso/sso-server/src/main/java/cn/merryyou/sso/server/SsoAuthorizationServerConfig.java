package cn.merryyou.sso.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Created on 2017/12/26.
 *
 * @author zlf
 * @since 1.0
 */
@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  /**
   * 客户端一些配置
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient("merryyou1")
        .secret(new BCryptPasswordEncoder().encode("merryyousecrect1"))
        .authorizedGrantTypes("authorization_code", "refresh_token")
//      .redirectUris("http://sso-taobao:8083/client1")
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
    endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
  }

  /**
   * springSecurity 授权表达式，访问merryyou tokenkey时需要经过认证
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("isAuthenticated()");
  }

  /**
   * JWTtokenStore
   */
  @Bean
  public TokenStore jwtTokenStore() {
    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  /**
   * 生成JTW token
   */
  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey("merryyou");
    return converter;
  }
}

//package cn.merryyou.sso.server;
//
//import cn.merryyou.sso.config.Jackson2SerializationStrategy;
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.jdbc.datasource.init.DataSourceInitializer;
//import org.springframework.jdbc.datasource.init.DatabasePopulator;
//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.provider.approval.ApprovalStore;
//import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
//@Configuration
//@EnableAuthorizationServer
//public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
//
//  @Autowired
//  private DataSource dataSource;
//
//  @Autowired
//  private RedisConnectionFactory redisConnectionFactory;
//
//  @Value("classpath:schema.sql")
//  private Resource schemaScript;
//
//  @Value("classpath:data.sql")
//  private Resource dataScript;
//
//  @Bean
//  public ApprovalStore approvalStore() {
//    return new JdbcApprovalStore(dataSource);
//  }
//
//  @Bean
//  protected AuthorizationCodeServices authorizationCodeServices() {
//    return new JdbcAuthorizationCodeServices(dataSource);
//  }
//
////  @Bean
////  public TokenStore tokenStore() {
////    return new JdbcTokenStore(dataSource);
////  }
//
//  @Override
//  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    clients.jdbc(dataSource);
//  }
//
//  @Override
//  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//    endpoints.approvalStore(approvalStore())
//        .authorizationCodeServices(authorizationCodeServices())
//        .tokenStore(tokenStore());
//  }
//
//  @Bean
//  public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
//    final DataSourceInitializer initializer = new DataSourceInitializer();
//    initializer.setDataSource(dataSource);
//    initializer.setDatabasePopulator(databasePopulator());
//    return initializer;
//  }
//
//  private DatabasePopulator databasePopulator() {
//    final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//    populator.addScript(schemaScript);
//    populator.addScript(dataScript);
//    return populator;
//  }
//
//  @Bean
//  public TokenStore tokenStore() {
//    RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
//    redisTokenStore.setSerializationStrategy(new Jackson2SerializationStrategy());
//    return redisTokenStore;
//  }
//}
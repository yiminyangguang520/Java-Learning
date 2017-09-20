package com.biostime.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;


@EnableResourceServer
@SpringBootApplication
@RestController
public class JdbcOauthWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcOauthWebApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private DataSource dataSource;
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManager).tokenStore(new JdbcTokenStore(dataSource)).approvalStoreDisabled();
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.allowFormAuthenticationForClients();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            /*   jdbc管理*/
          /*  clients.jdbc(dataSource).build();*/

            /*第一次初始化使用*/
            clients.jdbc(dataSource).withClient("jdbc-oauth")
            .authorizedGrantTypes("client_credentials", "password")
            .authorities("ROLE_CLIENT")
            .scopes("read")
            .resourceIds("jdbc-oauth2-resource")
            .secret("BT_2016@").accessTokenValiditySeconds(20);
        }
    }
}
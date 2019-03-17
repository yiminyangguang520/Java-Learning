package pl.piomin.services.gateway;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import pl.piomin.services.gateway.fallback.AccountFallbackProvider;
import pl.piomin.services.gateway.filter.AddResponseIDHeaderFilter;

/**
 * @author litz-a
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(GatewayApplication.class)
        .web(WebApplicationType.NONE)
        .run(args);
  }

  @Bean
  AddResponseIDHeaderFilter filter() {
    return new AddResponseIDHeaderFilter();
  }

  @Bean
  AccountFallbackProvider fallback() {
    return new AccountFallbackProvider();
  }

}

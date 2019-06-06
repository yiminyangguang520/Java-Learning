package org.lee.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author litz-a
 */
@Log4j2
@Component
public class RequestFilter implements GatewayFilter, Ordered {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    //String bodyFromRequest = RequestBodyKit.resolveBodyFromRequest(exchange.getRequest());
    //System.out.println("body is: " + bodyFromRequest);

    Object requestBody = exchange.getAttribute("cachedRequestBodyObject");
    log.info("request body is:{}", requestBody);

    return chain.filter(exchange).then(Mono.fromRunnable(() -> {
      System.out.println("RequestFilter post filter");
    }));
  }

  @Override
  public int getOrder() {
    return -5;
  }
}

package com.windmt.webflux.functional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author: min
 * @create: 2018-04-25
 **/
@Configuration
public class RouterConfig {

  @Bean
  @Autowired
  public RouterFunction<ServerResponse> routerFunction(final CalculatorHandler calculatorHandler) {
    return RouterFunctions.route(RequestPredicates.path("/calculator"), request ->
        request.queryParam("operator").map(operator ->
            Mono.justOrEmpty(ReflectionUtils.findMethod(CalculatorHandler.class, operator, ServerRequest.class))
                .flatMap(method -> (Mono<ServerResponse>) ReflectionUtils.invokeMethod(method, calculatorHandler, request))
                .switchIfEmpty(ServerResponse.badRequest().build())
                .onErrorResume(ex -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build()))
            .orElse(ServerResponse.badRequest().build()));
  }
}

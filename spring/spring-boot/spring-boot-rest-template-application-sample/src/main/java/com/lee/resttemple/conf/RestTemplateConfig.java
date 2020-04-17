package com.lee.resttemple.conf;

import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author min
 */
@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
    RestTemplate restTemplate = new RestTemplate(factory);
    StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
    restTemplate.getMessageConverters().add(0, converter);
    return restTemplate;
  }

  @Bean
  public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setReadTimeout(5000);
    factory.setConnectTimeout(15000);
    return factory;
  }
}

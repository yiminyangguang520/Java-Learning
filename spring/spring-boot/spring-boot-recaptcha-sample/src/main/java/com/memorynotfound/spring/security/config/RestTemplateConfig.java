package com.memorynotfound.spring.security.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author litz-a
 */
@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate(ClientHttpRequestFactory httpRequestFactory) {
    RestTemplate template = new RestTemplate(httpRequestFactory);
    template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    return template;
  }

  @Bean
  public ClientHttpRequestFactory httpRequestFactory(HttpClient httpClient) {
    return new HttpComponentsClientHttpRequestFactory(httpClient);
  }

  @Bean
  public HttpClient httpClient() {
    return HttpClientBuilder.create().build();
  }

}

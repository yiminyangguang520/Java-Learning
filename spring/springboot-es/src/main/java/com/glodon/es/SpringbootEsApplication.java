package com.glodon.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootEsApplication {

  @Bean
  public RestHighLevelClient getESRestClient() {
    RestClient restClient = RestClient.builder(
        new HttpHost("192.168.144.29", 9200, "http")).build();
    return new RestHighLevelClient(restClient);
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringbootEsApplication.class, args);
  }
}

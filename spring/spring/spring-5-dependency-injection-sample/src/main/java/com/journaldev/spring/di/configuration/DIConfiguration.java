package com.journaldev.spring.di.configuration;

import com.journaldev.spring.di.services.impl.EmailService;
import com.journaldev.spring.di.services.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author litz-a
 */
@Configuration
@ComponentScan(value = {"com.journaldev.spring.di.consumer"})
public class DIConfiguration {

  @Bean
  public MessageService messageService() {
    return new EmailService();
  }
}

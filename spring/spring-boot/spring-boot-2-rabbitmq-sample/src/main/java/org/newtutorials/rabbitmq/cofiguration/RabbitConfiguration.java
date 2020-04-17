package org.newtutorials.rabbitmq.cofiguration;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dani on 5/22/2017.
 *
 * @author min
 */
@Configuration
public class RabbitConfiguration {

  public static final String JSON_QUEUE_NAME = "jsonQueue";
  public static final String SERIALIZER_QUEUE_NAME = "serializerQueue";

  @Value("${spring.rabbitmq.host:#{null}}")
  String host;

  @Value("${spring.rabbitmq.port:#{null}}")
  Integer port;

  @Value("${spring.rabbitmq.username:#{null}}")
  String username;

  @Value("${spring.rabbitmq.password:#{null}}")
  String password;

  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
    connectionFactory.setUsername(username);
    connectionFactory.setPassword(password);
    connectionFactory.setConnectionTimeout(100);
    return connectionFactory;
  }


}

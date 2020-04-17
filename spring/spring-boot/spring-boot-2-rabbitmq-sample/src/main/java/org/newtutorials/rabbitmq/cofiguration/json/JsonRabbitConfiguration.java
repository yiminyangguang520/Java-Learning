package org.newtutorials.rabbitmq.cofiguration.json;

import java.util.HashMap;
import java.util.Map;
import org.newtutorials.rabbitmq.receiver.MessageReceiver;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dani on 5/22/2017.
 *
 * @author min
 */
@Configuration
public class JsonRabbitConfiguration {

  public static final String JSON_QUEUE_NAME = "jsonQueue";


  @Bean
  public RabbitTemplate rabbitTemplateJson(ConnectionFactory connectionFactory) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(new Jackson2JsonMessageConverter());
    return template;
  }

  @Bean
  public Queue jsonQueue() {
    Map<String, Object> args = new HashMap<>(1);
    args.put("x-message-ttl", 60000);
    return new Queue(JSON_QUEUE_NAME, true, false, false, args);
  }

  @Bean
  SimpleMessageListenerContainer jsonListenerContainer(ConnectionFactory connectionFactory,
      MessageListenerAdapter jsonMessageListenerAdapter,
      Queue jsonQueue) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueues(jsonQueue);
    container.setMessageListener(jsonMessageListenerAdapter);
    return container;
  }

  @Bean
  MessageListenerAdapter jsonMessageListenerAdapter(MessageReceiver jsonMessageListener) {
    MessageListenerAdapter receiveMessage = new MessageListenerAdapter(jsonMessageListener, "receiveMessage");
    receiveMessage.setMessageConverter(new Jackson2JsonMessageConverter());
    return receiveMessage;
  }

  @Bean
  MessageReceiver jsonMessageListener() {
    return new MessageReceiver(JSON_QUEUE_NAME);
  }
}

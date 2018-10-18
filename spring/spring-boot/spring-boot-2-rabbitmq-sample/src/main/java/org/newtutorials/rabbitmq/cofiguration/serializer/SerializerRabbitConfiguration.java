package org.newtutorials.rabbitmq.cofiguration.serializer;

import java.util.HashMap;
import java.util.Map;
import org.newtutorials.rabbitmq.receiver.MessageReceiver;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dani on 5/22/2017.
 *
 * @author litz-a
 */
@Configuration
public class SerializerRabbitConfiguration {

  public static final String SERIALIZER_QUEUE_NAME = "serializerQueue";

  @Bean
  public RabbitTemplate rabbitTemplateSerialize(ConnectionFactory connectionFactory) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(new SerializerMessageConverter());
    return template;
  }


  @Bean
  public Queue serializerQueue() {
    Map<String, Object> args = new HashMap<>(1);
    return new Queue(SERIALIZER_QUEUE_NAME, true, false, false, args);
  }


  @Bean
  SimpleMessageListenerContainer serializerListenerContainer(ConnectionFactory connectionFactory,
      MessageListenerAdapter serializerMessageListenerAdapter,
      Queue serializerQueue) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueues(serializerQueue);
    container.setMessageListener(serializerMessageListenerAdapter);
    return container;
  }

  @Bean
  MessageListenerAdapter serializerMessageListenerAdapter(MessageReceiver serializerMessageListener) {
    MessageListenerAdapter receiveMessage = new MessageListenerAdapter(serializerMessageListener, "receiveMessage");
    receiveMessage.setMessageConverter(new SerializerMessageConverter());
    return receiveMessage;
  }

  @Bean
  MessageReceiver serializerMessageListener() {
    return new MessageReceiver(SERIALIZER_QUEUE_NAME);
  }

}

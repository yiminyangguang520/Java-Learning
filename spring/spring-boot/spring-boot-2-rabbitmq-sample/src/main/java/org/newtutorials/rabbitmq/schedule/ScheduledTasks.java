package org.newtutorials.rabbitmq.schedule;

import org.newtutorials.rabbitmq.cofiguration.json.JsonRabbitConfiguration;
import org.newtutorials.rabbitmq.cofiguration.serializer.SerializerRabbitConfiguration;
import org.newtutorials.rabbitmq.message.SimpleMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by dani on 5/22/2017.
 *
 * @author min
 */
@Component
public class ScheduledTasks {

  @Autowired
  RabbitTemplate rabbitTemplateJson;

  @Autowired
  RabbitTemplate rabbitTemplateSerialize;

  @Scheduled(cron = "*/02 * * * * *")
  public void runIt() {
    SimpleMessage simpleMessage;
    simpleMessage = new SimpleMessage(System.currentTimeMillis(), "Json Message", 0);
    System.out.println("sending : " + simpleMessage);
    rabbitTemplateJson.convertAndSend(JsonRabbitConfiguration.JSON_QUEUE_NAME, simpleMessage);
    simpleMessage = new SimpleMessage(System.currentTimeMillis(), "Serializer Message", 0);
    System.out.println("sending : " + simpleMessage);
    rabbitTemplateSerialize.convertAndSend(SerializerRabbitConfiguration.SERIALIZER_QUEUE_NAME, simpleMessage);
  }
}

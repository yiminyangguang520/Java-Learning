package io.tpd.kafkaexample.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

/**
 * @author min
 */
@Configuration
public class KafkaConfig {

  @Autowired
  private KafkaProperties kafkaProperties;

  @Value("${tpd.topic-name}")
  private String topicName;

  // Producer configuration

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties());
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        JsonSerializer.class);
    return props;
  }

  @Bean
  public ProducerFactory<String, Object> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public NewTopic adviceTopic() {
    return new NewTopic(topicName, 3, (short) 1);
  }

  // Consumer configuration

  // If you only need one kind of deserialization, you only need to set the
  // Consumer configuration properties. Uncomment this and remove all others below.
//    @Bean
//    public Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>(
//                kafkaProperties.buildConsumerProperties()
//        );
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//                JsonDeserializer.class);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG,
//                "tpd-loggers");
//
//        return props;
//    }

  @Bean
  public ConsumerFactory<String, Object> consumerFactory() {
    final JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
    jsonDeserializer.addTrustedPackages("*");
    return new DefaultKafkaConsumerFactory<>(
        kafkaProperties.buildConsumerProperties(), new StringDeserializer(), jsonDeserializer
    );
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Object> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());

    return factory;
  }

  // String Consumer Configuration

  @Bean
  public ConsumerFactory<String, String> stringConsumerFactory() {
    return new DefaultKafkaConsumerFactory<>(
        kafkaProperties.buildConsumerProperties(), new StringDeserializer(), new StringDeserializer()
    );
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerStringContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(stringConsumerFactory());

    return factory;
  }

  // Byte Array Consumer Configuration

  @Bean
  public ConsumerFactory<String, byte[]> byteArrayConsumerFactory() {
    return new DefaultKafkaConsumerFactory<>(
        kafkaProperties.buildConsumerProperties(), new StringDeserializer(), new ByteArrayDeserializer()
    );
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, byte[]> kafkaListenerByteArrayContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, byte[]> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(byteArrayConsumerFactory());
    return factory;
  }
}

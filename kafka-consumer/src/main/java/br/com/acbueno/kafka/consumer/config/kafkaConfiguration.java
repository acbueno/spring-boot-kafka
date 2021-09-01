package br.com.acbueno.kafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.acbueno.kafka.consumer.model.Car;

@EnableKafka
@Configuration
public class kafkaConfiguration {

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {

    Map<String, Object> config = new HashMap<>();
    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

    return new DefaultKafkaConsumerFactory<>(config);

  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
        new ConcurrentKafkaListenerContainerFactory();
    factory.setConsumerFactory(consumerFactory());

    return factory;
  }

  @Bean
  public ConsumerFactory<String, Car> carConsumerFactory() {
    
    Map<String, Object> config = new HashMap<>();
    JsonDeserializer<Car> deserializer = new JsonDeserializer<>(Car.class);
    deserializer.setRemoveTypeHeaders(false);
    deserializer.addTrustedPackages("*");
    deserializer.setUseTypeMapperForKey(true);
    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
    config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
    config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, deserializer);

    return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
        deserializer);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Car> carKafkaListernerFacotry() {
    ConcurrentKafkaListenerContainerFactory<String, Car> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setMissingTopicsFatal(false);
    factory.setConsumerFactory(carConsumerFactory());


    return factory;
  }

}

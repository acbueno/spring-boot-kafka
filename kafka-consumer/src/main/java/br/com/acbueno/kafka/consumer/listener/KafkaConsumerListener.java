package br.com.acbueno.kafka.consumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import br.com.acbueno.kafka.consumer.model.Car;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j

public class KafkaConsumerListener {

  @KafkaListener(topics = "kafka-spring-producer", containerFactory = "carKafkaListernerFacotry")
  public void onUpdateReceived(ConsumerRecord<String, Car> consumerRecord) {
    String key = consumerRecord.key();
    log.info("key: " + consumerRecord.key());
    log.info("Headers: " + consumerRecord.headers());
    log.info("Partion: " + consumerRecord.partition());
    log.info("Order: " + consumerRecord.value());

  }

}

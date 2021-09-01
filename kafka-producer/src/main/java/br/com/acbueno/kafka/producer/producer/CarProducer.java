package br.com.acbueno.kafka.producer.producer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.acbueno.kafka.producer.controller.CarController;
import br.com.acbueno.kafka.producer.model.Car;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CarProducer {

  @Autowired
  private KafkaTemplate<String,Car> kafkaTemplate;

  private static final String TOPIC = "kafka-spring-producer";

  public void send(Car car) {
    ObjectMapper jsonObject = new ObjectMapper();
    final String messageKey = UUID.randomUUID().toString();
    kafkaTemplate.send(TOPIC, messageKey, car);
   
  }

}

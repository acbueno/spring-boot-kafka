package br.com.acbueno.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.acbueno.kafka.producer.model.Car;
import br.com.acbueno.kafka.producer.producer.CarProducer;

@Service
public class CarServices {

  @Autowired
  CarProducer carProducer;

  public void send(Car car) {
    carProducer.send(car);
  }

}

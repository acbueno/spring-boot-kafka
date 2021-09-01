package br.com.acbueno.kafka.producer.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.acbueno.kafka.producer.model.Car;
import br.com.acbueno.kafka.producer.service.CarServices;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class CarController {

  @Autowired
  CarServices carServices;
  

  @PostMapping(produces = {"application/json"}, consumes = {"application/json"}, value = "/create")
  public void send(@RequestBody Car car) {
    carServices.send(car);
  }
}

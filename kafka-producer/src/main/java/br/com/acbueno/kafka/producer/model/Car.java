package br.com.acbueno.kafka.producer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
  
  @JsonProperty("id")
  private Integer id;
  
  @JsonProperty("modelName")
  private String modelName;
  
  @JsonProperty("brandName")
  private String brandName;
  
  @JsonProperty("versionName")
  private String versionName;

}

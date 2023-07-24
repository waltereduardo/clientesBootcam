package com.nttdata.bootcam.banca.consulta.client.infraestructure.event;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Event<T> {
  private String id;
  private Date date;
  private EventType type;
  private T data;
}

package com.nttdata.bootcam.banca.consulta.client.infraestructure.event;

import com.nttdata.bootcam.banca.consulta.client.dto.ProductoCatalogoKafka;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientCreatedEvent extends Event<ProductoCatalogoKafka> {

}

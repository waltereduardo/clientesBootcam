package com.nttdata.bootcam.banca.consulta.client.infraestructure.event;

import com.nttdata.bootcam.banca.consulta.client.dto.event.BuyProductEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientCreatedEventCompra extends Event<BuyProductEvent>{
	private String topico;
}

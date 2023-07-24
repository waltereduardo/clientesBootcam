package com.nttdata.bootcam.banca.consulta.client.infraestructure;

import org.springframework.stereotype.Service;

import com.nttdata.bootcam.banca.consulta.client.dto.ProductoCatalogoKafka;

@Service
public class ClientServiceKafka {

	private final ClientEventsService clientEventsService;
	
	public ClientServiceKafka(ClientEventsService clientEventsService) {
		super();
		this.clientEventsService=clientEventsService;
	}
	

	public ProductoCatalogoKafka save(ProductoCatalogoKafka productoCatalogoKafka) {
		System.out.println("Received" + productoCatalogoKafka);
		this.clientEventsService.publish(productoCatalogoKafka);
		return productoCatalogoKafka;
		
	}
}

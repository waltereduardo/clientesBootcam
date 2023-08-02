package com.nttdata.bootcam.banca.consulta.client.dto.event;

import lombok.Data;

@Data
public class BuyProductEvent {
	private String typeClient;
	private String typeProduct;
	private String idClient;
	private String idProduct;
	private int cantidad;
	private String mensaje;
	private String id;
}

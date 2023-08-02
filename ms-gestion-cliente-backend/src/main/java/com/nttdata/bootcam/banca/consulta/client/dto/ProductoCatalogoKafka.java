package com.nttdata.bootcam.banca.consulta.client.dto;

import lombok.Data;

@Data
//@NoArgsConstructor
public class ProductoCatalogoKafka {
	/**
	 * 
	 */

	private String id;
	private String idTypeProduct;
	private String descCortaProduct;
	private String cantidad;
	private String precioUnitario;

}

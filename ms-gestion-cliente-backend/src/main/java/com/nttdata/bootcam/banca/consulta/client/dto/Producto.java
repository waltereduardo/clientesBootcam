package com.nttdata.bootcam.banca.consulta.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Producto {

	@JsonProperty("identificador")
	private String id;
	private String typeProduct;
	private String detTypeProduct;
	private String descDetTypeProduct;

}

package com.nttdata.bootcam.banca.consulta.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountClientResponse {

	@JsonProperty("identificador")
	private String idClient;
	private String idProduct;
	private String typeProduct;
}

package com.nttdata.bootcam.banca.consulta.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountClient {


	private String id;
	private String idClient;
	private String idProduct;
	private String typeProduct;
}

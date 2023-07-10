package com.nttdata.bootcam.banca.consulta.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientProductResponse {

	private String idclient;
	private String idproduct;
	private String state;
	
}

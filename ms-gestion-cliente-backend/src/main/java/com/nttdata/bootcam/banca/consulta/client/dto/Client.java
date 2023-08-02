package com.nttdata.bootcam.banca.consulta.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Client extends ClientResponse {

	@JsonProperty("identificador")
	private String id;
	private ClientResponse clientResponse;

}

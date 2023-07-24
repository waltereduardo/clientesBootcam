package com.nttdata.bootcam.banca.consulta.client.dto;



import lombok.Data;

@Data
public class AccountClient extends AccountClientResponse{


	private String id;
	private AccountClientResponse accountClientResponse;
//	private String idClient;
//	private String idProduct;
//	private String typeProduct;
}

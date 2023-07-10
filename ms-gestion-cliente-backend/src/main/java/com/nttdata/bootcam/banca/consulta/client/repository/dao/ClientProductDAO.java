package com.nttdata.bootcam.banca.consulta.client.repository.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("product-client")
public class ClientProductDAO {
	@Id
	private String id;
	private String idclient;
	private String idproduct;
	private String state;
}

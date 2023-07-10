package com.nttdata.bootcam.banca.consulta.client.repository.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("cuenta-cliente")
public class AccountClientDAO {
	@Id
	private String id;
	private String idClient;
	private String idProduct;
	private String typeProduct;
}

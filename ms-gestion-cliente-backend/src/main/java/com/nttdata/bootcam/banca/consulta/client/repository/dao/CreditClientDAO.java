package com.nttdata.bootcam.banca.consulta.client.repository.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("credito-cliente")
public class CreditClientDAO {
	@Id
	private String id;
	private String idClient;
	private String idProduct;
	private String typeCredit;
}

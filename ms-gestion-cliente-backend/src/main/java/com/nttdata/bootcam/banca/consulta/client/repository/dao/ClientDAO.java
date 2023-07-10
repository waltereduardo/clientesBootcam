package com.nttdata.bootcam.banca.consulta.client.repository.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("cliente")
public class ClientDAO {
	
	@Id
	private String id;
	private String typeDocument;
	private String numberDocument;
	private String typeClient;
	private String nameAll;

	
	
}

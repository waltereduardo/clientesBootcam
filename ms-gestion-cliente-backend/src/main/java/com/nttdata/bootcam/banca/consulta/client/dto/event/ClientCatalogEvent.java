package com.nttdata.bootcam.banca.consulta.client.dto.event;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class ClientCatalogEvent {
	private String typeDocument;
	private String numberDocument;
	private String typeClient;
	private String nameAll;
	private String mensaje;
	private String id;
}


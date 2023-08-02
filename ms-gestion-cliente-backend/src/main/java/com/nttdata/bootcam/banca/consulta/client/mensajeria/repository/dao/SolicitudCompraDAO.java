package com.nttdata.bootcam.banca.consulta.client.mensajeria.repository.dao;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "solicitud-compra")
public class SolicitudCompraDAO {
	@Id
	private String id;
	private String idMensaje;
	private String typeMensaje;
	private String dataMensaje;
	private String idCliente;
	private Date dateMensaje;
	private String topico;
}
